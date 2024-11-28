package com.ecommerce.pricing.domain.port.in;

import java.util.Date;

import com.ecommerce.pricing.domain.model.Price;

public interface PriceServicesPort {
	Price findPriceBetweenDates(Date dateSearch, int productId, int brandId);
}
