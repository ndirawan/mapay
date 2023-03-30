package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.*;
import com.enigma.mapay.repository.TransferRepository;
import com.enigma.mapay.service.TransferService;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.customResponds.Response;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    TransferRepository transferRepository;
    UserService userService;
    TransferDetailService transferDetailService;
    @Override
    public Transfer saveTransfer(Transfer transfer) {
        User sender = userService.getUserById(transfer.getUser().getId());
        User receiver = userService.getUserByPhoneNumb(transfer.getTransferDetail().getTransferTo());
        try {

//      Check if the sender exists
            if (sender == null) {
                throw new DataNotFoundException("Sender does not exist");
            }
//      Check if the receiver exists
            if (receiver == null) {
                throw new DataNotFoundException("Receiver does not exist");
            }
//      Check if the sender is trying to transfer to themselves
            if (sender.getPhoneNumber().equals(receiver.getPhoneNumber())) {
                throw new IllegalArgumentException("Sender cannot transfer to themselves");
            }

            if (sender.getBalance() < transfer.getTransferDetail().getAmount()) {
                throw new DataNotFoundException("Insufficient balance");
            }
            // ngurangin saldo dari sender balance
            sender.setBalance(sender.getBalance() - transfer.getTransferDetail().getAmount());

            // tambah amount ke receiver balance
            receiver.setBalance(receiver.getBalance() + transfer.getTransferDetail().getAmount());

            // Save sender and receiver
            userService.saveUser(sender);
            userService.saveUser(receiver);

            // Set the Transfer's sender and receiver
            transfer.setUser(sender);
            transfer.getTransferDetail().setTransferTo(receiver.getPhoneNumber());

            // Save Transfer and TransferDetail
            Transfer savedTransfer = transferRepository.save(transfer);
            transfer.getTransferDetail().setTransfer(savedTransfer);
            transferDetailService.saveTransferDetail(transfer.getTransferDetail());

            return savedTransfer;

        }catch (Exception e){
            e.getMessage();
            log.error(e.getMessage());
        }
        return null;
    }

    @Override

    public List<Transfer> getAllTransfer() {
        return transferRepository.findAll();
    }

    @Override

    public Transfer getTransferById(String id) {
        try {
            if (transferRepository.findById(id).isPresent()){
                return transferRepository.findById(id).get();
            }else throw new DataNotFoundException("DATA NOT FOUND");
        }catch (DataNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
