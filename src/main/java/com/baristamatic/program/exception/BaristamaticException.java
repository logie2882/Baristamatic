package com.baristamatic.program.exception;



import org.springframework.http.HttpStatus;

import com.baristamatic.program.utils.BaristamaticExceptionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaristamaticException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	
	   HttpStatus httpStatus;
	  
	  String errorCode;
	  
	  String errorDescrption;
	  
	  public BaristamaticException(BaristamaticExceptionEnum ex) {
		  
		  this.httpStatus = ex.getHttpStatus();
		  this.errorCode = ex.getServiceErrorCode();
		  this.errorDescrption = ex.getServiceErrorDescription();
	  
	  }
	 

}
