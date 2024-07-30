package com.retailer.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel {
	
    private Integer id;

    private Integer customerId;

    private LocalDate date;

    private Double amount;

}
