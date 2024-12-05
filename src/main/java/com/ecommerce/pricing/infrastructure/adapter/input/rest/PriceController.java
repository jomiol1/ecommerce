package com.ecommerce.pricing.infrastructure.adapter.input.rest;

import java.text.ParseException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.port.in.PriceServicesPort;
import com.ecommerce.pricing.infrastructure.adapter.input.rest.dto.PriceDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("v1/inditex")
public class PriceController {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	private final PriceServicesPort priceServices;
	
	
	public PriceController(PriceServicesPort priceServices){
		this.priceServices = priceServices;
	}

	@Operation(
	        summary = "Consultar precio de un producto",
	        description = "Devuelve el precio aplicable para un producto dado, en función de la fecha, el identificador del producto y el identificador de la cadena.")
	@GetMapping(value = "/price",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceDto> findPriceBetweenDates(
	         @Parameter(description = "Fecha de busqueda", example = "2020-06-14T18:00:00", 
             required = true,schema = @Schema(type = "string", format = "date-time"))
			@RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Date date,
			@Parameter(description = "ID del producto", example = "35455", required = true)
			@RequestParam int productId,
			@Parameter(description = "ID de la marca", example = "1", required = true)
			@RequestParam int brandId) throws ParseException{
		
		Price price = priceServices.findPriceBetweenDates(date, productId, brandId);
		return new ResponseEntity<>(RestMapper.toPriceDto(price), HttpStatus.OK);
		
	}
}
