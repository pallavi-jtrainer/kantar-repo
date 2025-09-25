package com.edforce.minibankapplication.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private Long customerId;
}
