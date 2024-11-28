package com.ecommerce.pricing.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.port.out.PricePersistencePort;

public class PriceServicesTest {
	
	@Mock
	private PricePersistencePort pricePersistence;

	@InjectMocks
	private PriceServices priceServices;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindPriceBetweenDatesOK() throws Exception {

    	Price price = Price.builder().id(1).brand("zara").currency("EUR").endDate(new Date())
    			.price("20").priority(1).product("test").startDate(new Date()).build();
        when(pricePersistence.findPriceBetweenDates(any(),anyInt(),anyInt())).thenReturn(price);
        
        Price priceResult = priceServices.findPriceBetweenDates(new Date(), 35455, 1);
        
        assertEquals(1, priceResult.getId());
        assertEquals("zara", priceResult.getBrand());
        assertEquals("EUR", priceResult.getCurrency());
        assertEquals("20", priceResult.getPrice());
        assertEquals(1, priceResult.getPriority());
        assertEquals("test", priceResult.getProduct());

    }
}
