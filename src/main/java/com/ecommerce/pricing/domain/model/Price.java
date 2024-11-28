package com.ecommerce.pricing.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Price {
    private int id;
    private String brand;
    private Date startDate;
    private Date endDate;
    private String product;
    private int priority;
    private String price;
    private String currency;
}
