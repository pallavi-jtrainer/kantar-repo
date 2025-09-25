package com.edforce.minibankapplication.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.minibankapplication.dto.TransactionDto;
import com.edforce.minibankapplication.dto.TransferRequest;
import com.edforce.minibankapplication.entity.Transaction;
import com.edforce.minibankapplication.mapper.DtoMapper;
import com.edforce.minibankapplication.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	 private final TransactionService transactionService;
	    private final DtoMapper mapper;

	    public TransactionController(TransactionService transactionService, DtoMapper mapper) {
	        this.transactionService = transactionService;
	        this.mapper = mapper;
	    }

	    @PostMapping("/{accountId}")
	    public ResponseEntity<TransactionDto> recordTransaction(
	            @PathVariable Long accountId,
	            @RequestParam BigDecimal amount,
	            @RequestParam String type,
	            @RequestParam(required = false) String description) {

	        Transaction tx = transactionService.recordTransaction(accountId, amount, type, description);
	        return ResponseEntity.ok(mapper.transactionToTransactionDto(tx));
	    }

	    @PostMapping("/transfer")
	    public ResponseEntity<Void> transfer(@Valid @RequestBody TransferRequest req) {
	        transactionService.transfer(req.getFromAccountNumber(), req.getToAccountNumber(), req.getAmount(), req.getDescription());
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/account/{accountId}")
	    public List<TransactionDto> getTransactions(@PathVariable Long accountId) {
	        return transactionService.getTransactionsForAccount(accountId).stream()
	        		.map(mapper::transactionToTransactionDto).collect(Collectors.toList());
	    }
}
