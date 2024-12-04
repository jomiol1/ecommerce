package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import java.util.Date;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"));
		PriceEntity priceEntity = priceRepository.findPriceBetweenDates(searchDate, productId, 
				brandId, pageable).get().findFirst().orElseThrow(PriceNotFound::new);
		
		return PersistenceMapper.toDomainModel(priceEntity);
	}

}
