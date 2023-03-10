package com.baristamatic.program.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaristamaticExceptionEnum {
	
	BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE,"1004","Service issue with the Downstream system failure."),
	BARISTAMATIC_INVALID_INVENTORY(HttpStatus.NOT_FOUND,"1003","Requested Drink not availabe , Less Ingredients in the inventory. "),
	BARISTAMATIC_INVALID_REQUEST(HttpStatus.NOT_FOUND,"1002","Requested Drink not availabe."),
	BARISTAMATIC_SYSTEM_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE,"1001","Baristamatic System Failure.");
	
	BaristamaticExceptionEnum(HttpStatus status, String errorCode, String errorDescription) {
		// TODO Auto-generated constructor stub
		this.httpStatus = status;
		this.serviceErrorCode = errorCode;
		this.serviceErrorDescription = errorDescription;
	}

	private final String serviceErrorCode; 
	
	private final String serviceErrorDescription;
	
	private final HttpStatus httpStatus;
	
}
