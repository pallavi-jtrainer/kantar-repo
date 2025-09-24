package com.edforce.minibankapplication.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto {
	private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String type;
    private LocalDateTime timestamp;
    private String description;
}
