package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.transaction.Transaction;
import com.demo.dtos.TransactionDTO;
import com.demo.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransationController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> criarTransacao(@RequestBody TransactionDTO transacao) throws Exception {

		Transaction newTransaction = this.transactionService.criarTransaction(transacao);
		return new ResponseEntity<>(newTransaction,HttpStatus.OK);
	}
}
