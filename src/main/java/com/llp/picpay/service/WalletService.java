package com.llp.picpay.service;

import com.llp.picpay.dto.CreateWalletDto;
import com.llp.picpay.entity.Wallet;
import com.llp.picpay.exception.WalletDataAlreadyExistsException;
import com.llp.picpay.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Service
public class WalletService {

    @Autowired
    private final WalletRepository walletRepository;


    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }


}
