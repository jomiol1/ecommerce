package com.ecommerce.pricing.infrastructure.adapter.input.rest;


import static com.ecommerce.pricing.domain.util.ErrorCatalog.DATE_FORMAT_ERROR;
import static com.ecommerce.pricing.domain.util.ErrorCatalog.GENERIC_ERROR;
import static com.ecommerce.pricing.domain.util.ErrorCatalog.PRICE_NOT_FOUND;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.pricing.domain.exception.PriceNotFound;
import com.ecommerce.pricing.domain.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandler {
	
	  @ResponseStatus(
	           value = HttpStatus.NOT_FOUND)
	  @org.springframework.web.bind.annotation.ExceptionHandler(value = {PriceNotFound.class })
	  public ErrorResponse handlePriceNotFoundException() {
	    return ErrorResponse.builder()
	        .code(PRICE_NOT_FOUND.getCode())
	        .message(PRICE_NOT_FOUND.getMessage())
	        .timestamp(LocalDateTime.now())
	        .build();
	  }
	  
	  @ResponseStatus(
	           value = HttpStatus.BAD_REQUEST)
	  @org.springframework.web.bind.annotation.ExceptionHandler(value = {ParseException.class })
	  public ErrorResponse handleDateBadformatException() {
	    return ErrorResponse.builder()
	        .code(DATE_FORMAT_ERROR.getCode())
	        .message(DATE_FORMAT_ERROR.getMessage())
	        .timestamp(LocalDateTime.now())
	        .build();
	  }
	  
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	  public ErrorResponse handleGenericError(Exception exception) {
	    return ErrorResponse.builder()
	        .code(GENERIC_ERROR.getCode())
	        .message(GENERIC_ERROR.getMessage())
	        .details(Collections.singletonList(exception.getMessage()))
	        .timestamp(LocalDateTime.now())
	        .build();
	  }

}
