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
import com.retailer.model.RewardPointsModel;
import com.retailer.model.TransactionModel;
import com.retailer.service.TransactionService;


/** 
 * This is the Controller class. 
 * Used for request the requirement
 * @apiNote the API URL starts with "/rewards"
 * 
 * @author manickavasagam.r
 * 
 */
@RestController
@RequestMapping("/rewards")
public class TransactionController {
	
	@Autowired
	private TransactionService tService;
	
	
	/**
	 *  
	 * This is the create transaction request. 
	 * @param transModel TransactionModel
	 * @exception CustomException used
	 * @return Transaction
	 * 
	 */
	
	@RequestMapping(value="/transaction", method = RequestMethod.POST)
	public Transaction createTransaction(@RequestBody TransactionModel transModel) throws CustomException{
		
		return tService.saveTransaction(transModel);
	}
	
	/**
	 *  
	 * This is the get transaction request by Id. 
	 * @param id Integer
	 * @exception CustomException used
	 * @return TransactionModel
	 * 
	 */
	
	@RequestMapping(value="/transaction/{id}", method = RequestMethod.GET)
	public TransactionModel getTransactionById(@PathVariable Integer id) throws CustomException{
		return tService.getTransactionById(id);
	}
	
	/**
	 *  
	 * This is the get transaction request by CustomerId. 
	 * @param cid Integer
	 * @exception CustomException used
	 * @return List<TransactionModel>
	 * 
	 */
	
	@RequestMapping(value="/customer/{cid}", method = RequestMethod.GET)
	public List<TransactionModel> getTransactionByCustomerId(@PathVariable Integer cid) throws CustomException{
		return tService.getTransactionByCustomerId(cid);
	}
	
	/**
	 *  
	 * This is the getRewardPointsForCustomer request by CustomerId. 
	 * @param cid Integer
	 * @exception CustomException used
	 * @return RewardPointsModel
	 * 
	 */
	
	@GetMapping("/customer") 
	public RewardPointsModel getRewardPointsForCustomer(@RequestParam("cid") Integer cid)  throws CustomException {
		return tService.getRewardPointsForCustomer(cid);
	}

}
