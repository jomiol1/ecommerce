package com.ecommerce.pricing.domain.port.out;

import java.util.Date;

import com.ecommerce.pricing.domain.model.Price;

public interface PricePersistencePort {
	
	Price findPriceBetweenDates(Date searchDate, int productId, int brandId);

}
