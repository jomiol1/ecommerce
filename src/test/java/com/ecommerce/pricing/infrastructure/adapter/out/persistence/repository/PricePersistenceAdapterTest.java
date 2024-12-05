package com.ecommerce.pricing.infrastructure.adapter.out.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.data.domain.Sort;

import com.ecommerce.pricing.domain.exception.PriceNotFound;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity.ProductEntity;

public class PricePersistenceAdapterTest {
	
	@Mock
	private PriceRepository priceRepository;
	
	@InjectMocks
	private PricePersistenceAdapter pricePersistence;
	
	Date dateSearch;
	Pageable pageable;
	Page<PriceEntity> pageResult;
	
    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        
	    String dateString = "2020-06-14T18:00:00";
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    dateSearch = formatter.parse(dateString);
	 
	    pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"));
		 
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
		
		pageResult = new PageImpl<>(priceEntityList, pageable, priceEntityList.size());
    }
    
    @Test
    void testFindPriceBetweenDatesOK() throws Exception {
    	
        when(priceRepository.findPriceBetweenDates(dateSearch,1,35455,pageable)).thenReturn(pageResult);
        
        Price priceResult = pricePersistence.findPriceBetweenDates(dateSearch, 1, 35455);
        
        assertEquals(1, priceResult.getId());
        assertEquals("zara", priceResult.getBrand());
        assertEquals("EUR", priceResult.getCurrency());
        assertEquals("20", priceResult.getPrice());
        assertEquals(1, priceResult.getPriority());
        assertEquals("test", priceResult.getProduct());
    }
    
    @Test
    void testFindPriceBetweenDatesNotFound() throws ParseException {

    	when(priceRepository.findPriceBetweenDates(dateSearch,1,1,pageable)).thenThrow(new PriceNotFound());
    	
	   assertThrows(PriceNotFound.class, () -> 
	    pricePersistence.findPriceBetweenDates(dateSearch, 1, 1)
	    );
    }

}
