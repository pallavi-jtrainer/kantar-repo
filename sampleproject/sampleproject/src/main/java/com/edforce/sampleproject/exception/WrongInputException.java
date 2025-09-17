package com.edforce.sampleproject.exception;

public class WrongInputException extends RuntimeException{

	public WrongInputException(String msg) {
		super(msg);
	}
}
