package com.edforce.minibankapplication.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

	private int status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> errors;
    
    public ApiError(int status, String message, List<String> errors) {
    	this.status = status;
    	this.message = message;
    	this.errors = errors;
    }
}
