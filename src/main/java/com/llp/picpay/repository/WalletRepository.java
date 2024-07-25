package com.llp.picpay.repository;

import com.llp.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {


    Optional<Wallet> findByCpfCnpjOrEmail(String s, String email);
}
