package com.retailer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	List<Transaction> findByCustomerIdAndDateBetween(Integer cutomerId, LocalDate startDate, LocalDate endDate);

	List<Transaction> findByCustomerId(Integer cId);

}
