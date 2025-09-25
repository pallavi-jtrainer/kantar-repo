package com.edforce.minibankapplication.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.minibankapplication.dto.AccountDto;
import com.edforce.minibankapplication.entity.Account;
import com.edforce.minibankapplication.mapper.DtoMapper;
import com.edforce.minibankapplication.services.AccountService;
import com.edforce.minibankapplication.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;
    private final CustomerService customerService;
    private final DtoMapper mapper;

    public AccountController(AccountService accountService, CustomerService customerService, DtoMapper mapper) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<AccountDto> createForCustomer(@PathVariable Long customerId, @Valid @RequestBody Account req) {
        Account account = new Account(req.getAccountNumber(), req.getAccountType(), req.getBalance());
        Account saved = customerService.addAccountToCustomer(customerId, account);
        AccountDto dto = mapper.accountToAccountDto(saved);
        return ResponseEntity.created(URI.create("/api/accounts/" + saved.getId())).body(dto);
    }

    @GetMapping("/by-number/{accountNumber}")
    public ResponseEntity<AccountDto> getByNumber(@PathVariable String accountNumber) {
        Account a = accountService.getByAccountNumber(accountNumber);
        return ResponseEntity.ok(mapper.accountToAccountDto(a));
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountDto> accountsForCustomer(@PathVariable Long customerId) {
        return accountService.getAccountsForCustomer(customerId).stream()
        		.map(mapper::accountToAccountDto).collect(Collectors.toList());
    }
}
