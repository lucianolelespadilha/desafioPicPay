package com.llp.picpay.controller;


import com.llp.picpay.dto.CreateWalletDto;
import com.llp.picpay.entity.Wallet;
import com.llp.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RestController
public class WalletController {

    @Autowired
    private final WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }




}