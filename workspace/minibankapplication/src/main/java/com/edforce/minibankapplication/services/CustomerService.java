package com.edforce.minibankapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edforce.minibankapplication.entity.Account;
import com.edforce.minibankapplication.entity.Customer;
import com.edforce.minibankapplication.exceptions.ResourceNotFoundException;
import com.edforce.minibankapplication.repository.AccountRepository;
import com.edforce.minibankapplication.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public List<Customer> listCustomers() { return customerRepository.findAll(); }

    @Transactional
    public Account addAccountToCustomer(Long customerId, Account account) {
        Customer customer = getCustomerById(customerId);
        customer.addAccount(account);
        customerRepository.save(customer);
        return account;
    }
}
