package com.bk.shop4.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean>handleResourceNotFoundException(ObjectNotFoundException ex){
		ResponseBean rb=new ResponseBean();
		rb.setCode(HttpStatus.NOT_FOUND.value());
		rb.setDescription(ex.getMessage());
		return new ResponseEntity<ResponseBean>(rb,HttpStatus.NOT_FOUND);
	}
}
