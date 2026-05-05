package com.it210_ss16.config;

import com.it210_ss16.model.entity.Wallet;
import com.it210_ss16.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class Data{

    @Bean
    CommandLineRunner initData(WalletRepository walletRepository) {
        return args -> {

            if (walletRepository.count() == 0) {

                Wallet w1 = new Wallet();
                w1.setBalance(new BigDecimal("100000"));

                Wallet w2 = new Wallet();
                w2.setBalance(new BigDecimal("50000"));

                Wallet w3 = new Wallet();
                w3.setBalance(new BigDecimal("200000"));

                walletRepository.save(w1);
                walletRepository.save(w2);
                walletRepository.save(w3);

            }
        };
    }
}