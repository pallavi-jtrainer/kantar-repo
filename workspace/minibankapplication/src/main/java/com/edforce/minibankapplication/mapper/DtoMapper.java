package com.edforce.minibankapplication.mapper;

import org.springframework.stereotype.Component;

import com.edforce.minibankapplication.dto.AccountDto;
import com.edforce.minibankapplication.dto.CustomerDto;
import com.edforce.minibankapplication.dto.TransactionDto;
import com.edforce.minibankapplication.entity.Account;
import com.edforce.minibankapplication.entity.Customer;
import com.edforce.minibankapplication.entity.Transaction;

@Component
public class DtoMapper {

	public CustomerDto customerToCustomerDto(Customer c) {
        if (c == null) return null;
        CustomerDto dto = new CustomerDto();
        dto.setId(c.getId());
        dto.setFirstName(c.getFirstName());
        dto.setLastName(c.getLastName());
        dto.setEmail(c.getEmail());
        return dto;
    }

    public AccountDto accountToAccountDto(Account a) {
        if (a == null) return null;
        AccountDto dto = new AccountDto();
        dto.setId(a.getId());
        dto.setAccountNumber(a.getAccountNumber());
        dto.setAccountType(a.getAccountType());
        dto.setBalance(a.getBalance());
        if (a.getCustomer() != null) dto.setCustomerId(a.getCustomer().getId());
        return dto;
    }

    public TransactionDto transactionToTransactionDto(Transaction t) {
        if (t == null) return null;
        TransactionDto dto = new TransactionDto();
        dto.setId(t.getId());
        if (t.getAccount() != null) dto.setAccountId(t.getAccount().getId());
        dto.setAmount(t.getAmount());
        dto.setType(t.getType());
        dto.setTimestamp(t.getTimestamp());
        dto.setDescription(t.getDescription());
        return dto;
    }
}
