package com.retailer.entity;

import java.time.LocalDate;

import com.retailer.exception.CustomException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  
 * Entity class represents a transaction 
 * 
 * A Transaction is a record of a customer's purchase
 * 
 * Here we using Lombok for @Data, @AllArgsConstructor, @NoArgsConstructor.
 *  so no need to add constructor, getters and setters.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
	
	/**
	 * 
	 * Unique Identifier for the transaction
	 * Generate the values  	
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer customerId;

    private LocalDate date;

    private Double amount;

}
