package com.ecommerce.pricing.infrastructure.adapter.out.persistence.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRICES")
@Data
public class PriceEntity {
    
	@Id
    @Column(name = "PRICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JoinColumn(name="BRAND_ID", referencedColumnName = "BRAND_ID", nullable = false)
    @ManyToOne
    private BrandEntity brand;
    
    @Column(name = "START_DATE")
    private Date startDate;
    
    @Column(name = "END_DATE")
    private Date endDate;
    
    @JoinColumn(name="PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    @ManyToOne
    private ProductEntity product;
    
    @Column(name = "PRIORITY")
    private int priority;
    
    @Column(name = "PRICE")
    private String price;

    @Column(name = "CURRENCY")
    private String currency;
	
}
