package com.it210_ss16.service;

import com.it210_ss16.model.entity.Wallet;
import com.it210_ss16.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void transferMoney(Long fromWalletId, Long toWalletId, BigDecimal amount) {

        Wallet walletA = walletRepository.findById(fromWalletId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ví A"));

        Wallet walletB = walletRepository.findById(toWalletId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ví B"));

        if (walletA.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Ví A không đủ tiền");
        }

        walletA.setBalance(walletA.getBalance().subtract(amount));
        walletB.setBalance(walletB.getBalance().add(amount));

        walletRepository.save(walletA);
        walletRepository.save(walletB);
    }
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
}