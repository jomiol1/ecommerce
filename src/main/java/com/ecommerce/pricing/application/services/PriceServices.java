package com.ecommerce.pricing.application.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.port.in.PriceServicesPort;
import com.ecommerce.pricing.domain.port.out.PricePersistencePort;

@Service
public class PriceServices implements PriceServicesPort {

	private PricePersistencePort pricePersistence;
	
	public PriceServices(PricePersistencePort pricePersistence){
		this.pricePersistence=pricePersistence;
	}

	@Override
	public Price findPriceBetweenDates(Date dateSearch, int productId, int brandId) {
		return pricePersistence.findPriceBetweenDates(dateSearch, productId, brandId);
	}
	
}
