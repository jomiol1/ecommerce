package com.ecommerce.pricing.infrastructure.adapter.input.rest;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.input.rest.dto.PriceDto;

public class RestMapper {
	
	public static PriceDto toPriceDto(Price price){
		return PriceDto.builder().id(price.getId()).brand(price.getBrand())
				.product(price.getProduct()).price(price.getPrice())
				.startDate(price.getStartDate()).endDate(price.getEndDate())
				.build();
	}

}
