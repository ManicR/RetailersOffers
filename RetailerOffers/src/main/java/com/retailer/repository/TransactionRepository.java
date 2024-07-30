package com.retailer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.entity.Transaction;

/**
 *  
 * Repository interface for Transaction entity. 
 * 
 * Providing methods for accessing and manipulating transactions in the database.
 * 
 * 
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	/**
	 *  
	 * find transactions for a given customer id
	 * @param cId customerId
	 * 
	 */
	
	List<Transaction> findByCustomerId(Integer cId);

}
