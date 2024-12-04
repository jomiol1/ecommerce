package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.ProductEntity;

public class PricePersistenceAdapterTest {
	
	@Mock
	private PriceRepository priceRepository;
	
	@InjectMocks
	private PricePersistenceAdapter pricePersistence;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindPriceBetweenDatesOK() throws Exception {
    	
    	BrandEntity brandEntity = new BrandEntity();
    	brandEntity.setId(1);
    	brandEntity.setName("zara");
    	
    	ProductEntity productEntity = new ProductEntity();
    	productEntity.setId(1);
    	productEntity.setName("test");
    	
    	PriceEntity priceEntity = new PriceEntity();
    	priceEntity.setId(1);
    	priceEntity.setBrand(brandEntity);
    	priceEntity.setProduct(productEntity);
    	priceEntity.setCurrency("EUR");
    	priceEntity.setStartDate(new Date());
    	priceEntity.setEndDate(new Date());
    	priceEntity.setPrice("20");
    	priceEntity.setPriority(1);
    	
    	List<PriceEntity> priceEntityList = new ArrayList<>();
    	priceEntityList.add(priceEntity);
    	
    	Pageable pageable = PageRequest.of(0, 1);
    	Page<PriceEntity> pageResult = new PageImpl<>(priceEntityList, pageable, priceEntityList.size());

        when(priceRepository.findPriceBetweenDates(any(),anyInt(),anyInt(),any())).thenReturn(pageResult);
        
        Price priceResult = pricePersistence.findPriceBetweenDates(new Date(), 1, 1);
        
        assertEquals(1, priceResult.getId());
        assertEquals("zara", priceResult.getBrand());
        assertEquals("EUR", priceResult.getCurrency());
        assertEquals("20", priceResult.getPrice());
        assertEquals(1, priceResult.getPriority());
        assertEquals("test", priceResult.getProduct());


    }

}
