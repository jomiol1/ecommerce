package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;


@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Integer>{

	 @Query("SELECT p FROM PriceEntity p "
	 		+ "WHERE ?1 BETWEEN p.startDate AND p.endDate "
	 		+ "AND p.product.id = ?2 "
	 		+ "AND p.brand.id = ?3 "
	 		+ "ORDER BY p.priority DESC "
	 		+ "LIMIT 1")
	 Optional<PriceEntity> findPriceBetweenDates(Date searchDate, int productId, int brandId);
	 
	 
}
