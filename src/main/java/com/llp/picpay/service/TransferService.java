package com.llp.picpay.service;

import com.llp.picpay.dto.TransferDto;
import com.llp.picpay.entity.Transfer;
import com.llp.picpay.entity.Wallet;
import com.llp.picpay.exception.InsufficientBalanceException;
import com.llp.picpay.exception.TranferNotAuthorizedException;
import com.llp.picpay.exception.TransferNotAllowedForWalletTypeException;
import com.llp.picpay.exception.WalletNotFoundException;
import com.llp.picpay.repository.TransferRepository;
import com.llp.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Service
public class TransferService {


    private final AuthorizationService authorizationService;

    private final NotificationService notificationService;

    private final TransferRepository transferRepository;

    private final WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));


        validadeTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());


        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(()-> notificationService.sendNotification(transferResult));

        return transferResult;

    }

    private void validadeTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TranferNotAuthorizedException();
        }
    }


}



