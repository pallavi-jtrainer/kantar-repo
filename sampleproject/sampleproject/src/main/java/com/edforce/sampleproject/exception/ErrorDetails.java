package com.edforce.sampleproject.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor
@ToString
@NoArgsConstructor
public class ErrorDetails {
	private int statusCode;
	private String message;
	private LocalDateTime timestamp;
}
