package com.enigma.mapay.controller;

import com.enigma.mapay.dto.MidtransTrxResponse;
import com.enigma.mapay.config.MidtransConfig;
import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.TopupDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.security.JwtUtils;
import com.enigma.mapay.service.TopupDetailService;
import com.enigma.mapay.service.TopupService;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.service.impl.UserDetailImpl;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import com.midtrans.httpclient.error.MidtransError;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiUrlConstant.TOPUP_PATH)
@AllArgsConstructor
public class TopupController {

    TopupService topupService;
    MidtransConfig midtransConfig;
    TopupDetailService detailService;
    UserService userService;
    JwtUtils jwtUtils;

    @PostMapping
    public MidtransTrxResponse saveTopup(@RequestBody Topup topup, Authentication authentication) throws MidtransError{
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        topup.setUser(userService.getUserByPhoneNumb(userDetail.getUsername()));
        return topupService.saveTopup(topup);
    }
    @GetMapping
    public List<Topup> getAllTopup(Authentication authentication){
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        return topupService.getAllTopUp(userDetail.getUsername());
    }

    @GetMapping("/{id}")
    public Topup findById(@PathVariable String id){

        return topupService.getTopupById(id);
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<Object> topuMidts(@PathVariable String orderId) throws MidtransError {
        Object object = midtransConfig.coreApi().checkTransaction(orderId).toMap();
        return ResponseEntity.ok(object);
    }
    @PostMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> handleNotification(@RequestBody Map<String, Object> response) throws MidtransError {
        String notifResponse = null;
        if (!(response.isEmpty())) {
            //Get Order ID from notification body
            String orderId = (String) response.get("order_id");
            String methode = (String) response.get("payment_type");

            // Get status transaction to api with order id
            JSONObject transactionResult = midtransConfig.coreApi().checkTransaction(orderId);

            String transactionStatus = (String) transactionResult.get("transaction_status");
            String fraudStatus = (String) transactionResult.get("fraud_status");

            notifResponse = "Transaction notification received. Order ID: " + orderId + ". Transaction status: " + transactionStatus + ". Fraud status: " + fraudStatus;
            System.out.println(notifResponse);

            TopupDetail topupDetail = topupService.getTopupById(orderId).getTopupDetail();
            User user = userService.getUserById(topupService.getTopupById(orderId).getUser().getId());
            topupDetail.setMethode(methode);

            switch (transactionStatus) {
                case "capture":
                    switch (fraudStatus) {
                        case "challenge":
                            topupDetail.setStatus("challenge");
                            break;
                        case "accept":
                            topupDetail.setStatus("accept");
                            user.setBalance(user.getBalance() + topupDetail.getAmount());
                            break;
                    }
                    break;
                case "cancel":
                case "deny":
                case "expire":
                    topupDetail.setStatus("failed");
                    break;
                case "pending":
                    topupDetail.setStatus("pending");
                    break;
            }
            detailService.saveTopupDetail(topupDetail);
            userService.saveUser(user);
        }
        return new ResponseEntity<>(notifResponse, HttpStatus.OK);
    }
}
