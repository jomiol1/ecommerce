package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository.PricePersistenceAdapter;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository.PriceRepository;

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
    	
    	Optional<PriceEntity> priceResultOptional = Optional.of(priceEntity);

        when(priceRepository.findPriceBetweenDates(any(),anyInt(),anyInt())).thenReturn(priceResultOptional);
        
        Price priceResult = pricePersistence.findPriceBetweenDates(new Date(), 1, 1);
        
        assertEquals(1, priceResult.getId());
        assertEquals("zara", priceResult.getBrand());
        assertEquals("EUR", priceResult.getCurrency());
        assertEquals("20", priceResult.getPrice());
        assertEquals(1, priceResult.getPriority());
        assertEquals("test", priceResult.getProduct());


    }

}
