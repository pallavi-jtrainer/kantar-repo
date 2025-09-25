package com.edforce.minibankapplication.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edforce.minibankapplication.entity.Account;
import com.edforce.minibankapplication.entity.Transaction;
import com.edforce.minibankapplication.exceptions.InsufficientFundsException;
import com.edforce.minibankapplication.exceptions.ResourceNotFoundException;
import com.edforce.minibankapplication.repository.AccountRepository;
import com.edforce.minibankapplication.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction recordTransaction(Long accountId, BigDecimal amount, String type, String description) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + accountId));

        if ("DEPOSIT".equalsIgnoreCase(type)) {
            account.setBalance(account.getBalance().add(amount));
        } else if ("WITHDRAWAL".equalsIgnoreCase(type)) {
            if (account.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient balance");
            }
            account.setBalance(account.getBalance().subtract(amount));
        } else {
            throw new IllegalArgumentException("Unsupported transaction type: " + type);
        }

        accountRepository.save(account);

        Transaction tx = new Transaction(account, amount, type, description);
        return transactionRepository.save(tx);
    }

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount, String description) {
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        Account from = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("From account not found: " + fromAccountNumber));
        Account to = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("To account not found: " + toAccountNumber));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in " + fromAccountNumber);
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction txFrom = new Transaction(from, amount.negate(), "TRANSFER_OUT", description);
        Transaction txTo = new Transaction(to, amount, "TRANSFER_IN", description);

        transactionRepository.save(txFrom);
        transactionRepository.save(txTo);
    }

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }
}
