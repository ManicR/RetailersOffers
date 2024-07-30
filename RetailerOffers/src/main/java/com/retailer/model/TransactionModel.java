package com.retailer.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  
 * Model class represents a public class TransactionModel  
 * 
 * TransactionModel is DTO class for Transaction Entity.
 * 
 * Here we using Lombok for @Data, @AllArgsConstructor, @NoArgsConstructor.
 *  so no need to add constructor, getters and setters.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel {
	
    private Integer id;

    private Integer customerId;

    private LocalDate date;

    private Double amount;

}
