package com.llp.picpay.config;

import com.llp.picpay.entity.WalletType;
import com.llp.picpay.repository.WalletTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@AllArgsConstructor
@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final WalletTypeRepository walletTypeRepository;



    @Override
    public void run(String... args) throws Exception {

       Arrays.stream(WalletType.Enum.values())
               .forEach(walletType -> walletTypeRepository.save(walletType.get()));
    }
}
