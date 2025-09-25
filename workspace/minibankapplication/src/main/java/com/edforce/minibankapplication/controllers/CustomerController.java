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

import com.edforce.minibankapplication.dto.CustomerDto;
import com.edforce.minibankapplication.entity.Customer;
import com.edforce.minibankapplication.mapper.DtoMapper;
import com.edforce.minibankapplication.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;
    private final DtoMapper mapper;

    public CustomerController(CustomerService customerService, DtoMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody Customer c) {
        Customer saved = customerService.createCustomer(c);
        CustomerDto dto = mapper.customerToCustomerDto(saved);
        return ResponseEntity.created(URI.create("/api/customers/" + saved.getId())).body(dto);
    }

    @GetMapping
    public List<CustomerDto> list() {
        return customerService.listCustomers().stream().map(mapper::customerToCustomerDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        Customer c = customerService.getCustomerById(id);
        return ResponseEntity.ok(mapper.customerToCustomerDto(c));
    }
}
