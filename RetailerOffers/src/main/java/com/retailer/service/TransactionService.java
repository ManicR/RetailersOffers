package com.retailer.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.entity.Transaction;
import com.retailer.exception.CustomException;
import com.retailer.model.RewardPointsModel;
import com.retailer.model.TransactionModel;
import com.retailer.repository.TransactionRepository;


/** 
 * This is the service class. 
 * Used for calculate reward points of customers
 * it has business logics for create , get transactions based on request 
 * 
 * @author manickavasagam.r
 * 
 */
@Service
public class TransactionService {
	
	
	@Autowired
	private TransactionRepository tRepo;
	
	/**
	 *  
	 * This is the save transaction method. 
	 * @param transModel TransactionModel
	 * 
	 */
	
	public Transaction saveTransaction(TransactionModel transModel) {
		Transaction t = transactionModelToEntity(transModel);
		return tRepo.saveAndFlush(t);		 
	}

	/**
	 *  
	 * This is the get transaction method by Id. 
	 * @param id Integer 
	 * 
	 */
	
	public TransactionModel getTransactionById(Integer id) {
		Optional<Transaction> transaction = tRepo.findById(id);
		if(transaction.isPresent())
			return entityToTransactionModel(transaction.get());
		
		return null;
	}

	/**
	 *  
	 * This is the get transaction method by CustomerId. 
	 * @param cid Integer 
	 * 
	 */
	
	public List<TransactionModel> getTransactionByCustomerId(Integer cId) {
		List<Transaction> transactions = tRepo.findByCustomerId(cId);
		if(!transactions.isEmpty() && transactions!= null) {
			return transactions.stream().map(a -> entityToTransactionModel(a)).collect(Collectors.toList());
		}
			
		
		return null;
	}
	
	
	/** 
	 * This is the getRewardPointsForCustomer method. 
	 * Used for calculate reward points of customers
	 * @param cid Integer 
	 * 
	 * @author manickavasagam.r
	 * 
	 */
	
	public RewardPointsModel getRewardPointsForCustomer(Integer cutomerId)  throws CustomException {
	
		List<TransactionModel> transactions = getTransactionByCustomerId(cutomerId);
		
		Map<String , Integer> monthlyPoints = calculateMonthlyPoints(transactions);
		
		Integer totalPoints = calculateTotalPoints(transactions);
		
		return new RewardPointsModel(monthlyPoints , totalPoints);
		
	}
	
	/** 
	 * This is the calculateTotalPoints method.
	 * It will return the total points
	 * 
	 */
	private Integer calculateTotalPoints(List<TransactionModel> transactions) {
		
		return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();	
		
	}

	/** 
	 * This is the calculateMonthlyPoints method.
	 * It will return the monthly points
	 * 
	 */
	private Map<String, Integer> calculateMonthlyPoints(List<TransactionModel> transactions) {
		Map<String , Integer> monthlyPoints = new HashMap<>();
		
		transactions.stream().forEach(t -> {
		String month = getMonthName(t.getDate().getMonthValue());
		Integer points = calculatePoints(t.getAmount());
		monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
		});
		return monthlyPoints;
	}

	/** 
	 * This is the getMonthName method.
	 * It will return the month name
	 * 
	 */
	
	private String getMonthName(Integer month) {
		return LocalDate.of(2024, month, 1).format(DateTimeFormatter.ofPattern("MMMM"));
	}
	
	/** 
	 * This is the calculatePoints method.
	 * It will return the point based on amount
	 * 
	 */
	
	public Integer calculatePoints(Double amount) {
		Integer points = 0;
		if(amount > 100) {
			
			points += (int) (amount - 100) * 2 ;
			amount = 100.0;
		}
		if( amount > 50) {
			points +=(int) (amount - 50);
		}
		return points;
	}
	
	/** 
	 * This is the TransactionModelToEntity method.
	 * This method will act as Method mapper.
	 * It will return the Transaction for TransactionModel
	 * 
	 */
	
	public Transaction transactionModelToEntity(TransactionModel transModel) {
		Transaction t = new Transaction();
		t.setId(transModel.getId());
		t.setCustomerId(transModel.getCustomerId());
		t.setDate(transModel.getDate());
		t.setAmount(transModel.getAmount());
		return t;
	}
	
	/** 
	 * This is the EntityToTransactionModel method.
	 * This method will act as Method mapper.
	 * It will return the TransactionModel for Transaction
	 * 
	 */
	
	public TransactionModel entityToTransactionModel(Transaction t) {
		TransactionModel tm = new TransactionModel();
		tm.setId(t.getId());
		tm.setCustomerId(t.getCustomerId());
		tm.setDate(t.getDate());
		tm.setAmount(t.getAmount());
		return tm;
	}



}
