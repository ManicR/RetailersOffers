package com.retailer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.entity.Transaction;
import com.retailer.exception.CustomException;
import com.retailer.model.TransactionModel;
import com.retailer.service.TransactionService;

@RestController
@RequestMapping("/rewards")
public class TransactionController {
	
	@Autowired
	private TransactionService tService;
	
	@RequestMapping(value="/transaction", method = RequestMethod.POST)
	public Transaction createTransaction(@RequestBody TransactionModel transModel) throws CustomException{
		
		return tService.saveTransaction(transModel);
	}
	
	@RequestMapping(value="/transaction/{id}", method = RequestMethod.GET)
	public TransactionModel getTransactionById(@PathVariable Integer id) throws CustomException{
		return tService.getTransactionById(id);
	}
	
	@RequestMapping(value="/customer/{cid}", method = RequestMethod.GET)
	public List<TransactionModel> getTransactionByCustomerId(@PathVariable Integer cid) throws CustomException{
		return tService.getTransactionByCustomerId(cid);
	}
	
	@GetMapping("/customer") 
	public Integer getRewardPointsForCustomer(@RequestParam("cid") Integer cid,
			@RequestParam("month") Integer month, @RequestParam("year") Integer year)  throws CustomException {
		return tService.getRewardPointsForCustomer(cid, month, year);
	}

}
