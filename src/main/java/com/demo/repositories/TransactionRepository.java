package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
