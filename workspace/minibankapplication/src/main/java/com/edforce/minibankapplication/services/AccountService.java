package com.edforce.minibankapplication.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edforce.minibankapplication.entity.Account;
import com.edforce.minibankapplication.exceptions.ResourceNotFoundException;
import com.edforce.minibankapplication.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + accountNumber));
    }

    public List<Account> getAccountsForCustomer(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    @Transactional
    public boolean updateBalance(String accountNumber, BigDecimal newBalance) {
        int rows = accountRepository.updateBalanceByAccountNumber(accountNumber, newBalance);
        return rows > 0;
    }
}
