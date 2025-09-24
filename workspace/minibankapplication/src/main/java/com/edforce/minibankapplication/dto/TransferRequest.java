package com.edforce.minibankapplication.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
	@NotBlank
    private String fromAccountNumber;

    @NotBlank
    private String toAccountNumber;

    @NotNull
    private BigDecimal amount;

    private String description;
}
