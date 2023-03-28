package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.*;
import com.enigma.mapay.repository.TransferRepository;
import com.enigma.mapay.service.TransferService;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import io.sentry.spring.tracing.SentrySpan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    TransferRepository transferRepository;

    UserService userService;

    TransferDetailService transferDetailService;
    @Override

    public Transfer saveTransfer(Transfer transfer) {
        User sender = userService.getUserById(transfer.getUser().getId());
        User receiver = userService.getUserByPhoneNumb(transfer.getTransferDetail().getTransferTo());

//      Check if the sender exists
        if(sender == null){
            throw new DataNotFoundException("Sender does not exist");
        }
//      Check if the receiver exists
        if(receiver == null){
            throw new DataNotFoundException("Receiver does not exist");
        }
//      Check if the sender is trying to transfer to themselves
        if(sender.getPhoneNumb().equals(receiver.getPhoneNumb())){
            throw new IllegalArgumentException("Sender cannot transfer to themselves");
        }

        if (sender.getBalance() < transfer.getTransferDetail().getAmount()){
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
        transfer.getTransferDetail().setTransferTo(receiver.getPhoneNumb());

        // Save Transfer and TransferDetail
        Transfer savedTransfer = transferRepository.save(transfer);
        transfer.getTransferDetail().setTransfer(savedTransfer);
        transferDetailService.saveTransferDetail(transfer.getTransferDetail());

        return savedTransfer;
    }

    @Override

    public List<Transfer> getAllTransfer() {
        return transferRepository.findAll();
    }

    @Override

    public Transfer getTransferById(String id) {
        return transferRepository.findById(id).get();
    }
}
