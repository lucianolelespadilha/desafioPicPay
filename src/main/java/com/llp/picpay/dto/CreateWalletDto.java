package com.llp.picpay.dto;

import com.llp.picpay.entity.Wallet;
import com.llp.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public record CreateWalletDto(
        @NotBlank
        String fullName,
        @NotBlank
        String cpfCnpj,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        WalletType.Enum walletType) {


    public Wallet toWallet(){
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }

}
