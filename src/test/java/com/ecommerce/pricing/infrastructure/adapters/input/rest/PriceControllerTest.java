package com.ecommerce.pricing.infrastructure.adapters.input.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
    
    @Test
    void testFindPriceBetweenDatesCaseOne() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-14T10:00:00&productId=35455&brandId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Zara"))
                .andExpect(jsonPath("$.product").value("camisa lino"))
                .andExpect(jsonPath("$.price").value("35.50"));
     
    }
    
    @Test
    void testFindPriceBetweenDatesCaseTwo() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-14T16:00:00&productId=35455&brandId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.brand").value("Zara"))
                .andExpect(jsonPath("$.product").value("camisa lino"))
                .andExpect(jsonPath("$.price").value("25.45"));
     
    }
    
    @Test
    void testFindPriceBetweenDatesCaseThree() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-14T21:00:00&productId=35455&brandId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Zara"))
                .andExpect(jsonPath("$.product").value("camisa lino"))
                .andExpect(jsonPath("$.price").value("35.50"));
     
    }
    
    @Test
    void testFindPriceBetweenDatesCaseFour() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-15T10:00:00&productId=35455&brandId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.brand").value("Zara"))
                .andExpect(jsonPath("$.product").value("camisa lino"))
                .andExpect(jsonPath("$.price").value("30.50"));
     
    }
    
    @Test
    void testFindPriceBetweenDatesCaseFive() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-16T21:00:00&productId=35455&brandId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.brand").value("Zara"))
                .andExpect(jsonPath("$.product").value("camisa lino"))
                .andExpect(jsonPath("$.price").value("38.95"));
     
    }
    
    @Test
    void testFindPriceBetweenDatesNotFound() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-14T10:00:00&productId=35455&brandId=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testFindPriceBetweenDatesBadRequest() throws Exception {

        mockMvc.perform(get("/v1/inditex/price?date=2020-06-14-10-00-00&productId=35455&brandId=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
