package com.ecommerce.pricing.domain.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

	GENERIC_ERROR("00", "An unexpected error occurred."),
	PRICE_NOT_FOUND("01", "Price not found."),
	DATE_FORMAT_ERROR("02", "Date format error.");

  private final String code;
  private final String message;

  ErrorCatalog(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
