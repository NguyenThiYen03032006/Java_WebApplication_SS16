package com.it210_ss16.repository;

import com.it210_ss16.model.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    Page<TransactionHistory> findByWalletId(Long walletId, Pageable pageable);

    @Query("SELECT t FROM TransactionHistory t WHERE t.amount > :minAmount")
    List<TransactionHistory> findLargeTransactions(@Param("minAmount") BigDecimal minAmount);
}