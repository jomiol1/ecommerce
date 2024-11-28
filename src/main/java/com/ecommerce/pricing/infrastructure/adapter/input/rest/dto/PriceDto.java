package com.ecommerce.pricing.infrastructure.adapter.input.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PriceDto {
	private int id;
    private String product;
    private String brand;
    private String price;
    private Date startDate;
    private Date endDate;

}
