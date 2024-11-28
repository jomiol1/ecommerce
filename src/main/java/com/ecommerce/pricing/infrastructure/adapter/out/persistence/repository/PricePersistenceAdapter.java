package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ecommerce.pricing.domain.exception.PriceNotFound;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.port.out.PricePersistencePort;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;

@Service
public class PricePersistenceAdapter implements PricePersistencePort {

	private PriceRepository priceRepository;
	
	public PricePersistenceAdapter(PriceRepository priceRepository){
		this.priceRepository = priceRepository;
	}
	
	@Override
	public Price findPriceBetweenDates(Date searchDate, int productId, int brandId) {
		
		PriceEntity priceEntity =  priceRepository.findPriceBetweenDates(searchDate, productId, brandId)
				.orElseThrow(PriceNotFound::new);
		
		return PersistenceMapper.toDomainModel(priceEntity);
	}

}
