package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;


@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Integer>{

	 @Query("SELECT p FROM PriceEntity p "
	 		+ "WHERE ?1 BETWEEN p.startDate AND p.endDate "
	 		+ "AND p.product.id = ?2 "
	 		+ "AND p.brand.id = ?3 ")
	 Page<PriceEntity> findPriceBetweenDates(Date searchDate, int productId, 
			 int brandId, Pageable pageable);
	 
}
