package com.retailer.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.entity.Transaction;
import com.retailer.exception.CustomException;
import com.retailer.model.TransactionModel;
import com.retailer.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository tRepo;
	
	public Integer getRewardPointsForCustomer(Integer cutomerId, Integer month , Integer year)  throws CustomException {
		
		LocalDate startDate = LocalDate.of(year, month, 1);
		
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		
		List<Transaction> transactions = tRepo.findByCustomerIdAndDateBetween(cutomerId , startDate , endDate);
		
		return transactions.stream().mapToInt(this::calculatePoints).sum();
		
	}
	
	public Integer calculatePoints(Transaction t) {
		Double amount = t.getAmount();
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
	
	public Transaction TransactionModelToEntity(TransactionModel transModel) {
		Transaction t = new Transaction();
		t.setId(transModel.getId());
		t.setCustomerId(transModel.getCustomerId());
		t.setDate(transModel.getDate());
		t.setAmount(transModel.getAmount());
		return t;
	}
	
	public TransactionModel EntityToTransactionModel(Transaction t) {
		TransactionModel tm = new TransactionModel();
		tm.setId(t.getId());
		tm.setCustomerId(t.getCustomerId());
		tm.setDate(t.getDate());
		tm.setAmount(t.getAmount());
		return tm;
	}

	public Transaction saveTransaction(TransactionModel transModel) {
		Transaction t = TransactionModelToEntity(transModel);
		return tRepo.saveAndFlush(t);		 
	}

	public TransactionModel getTransactionById(Integer id) {
		Optional<Transaction> transaction = tRepo.findById(id);
		if(transaction.isPresent())
			return EntityToTransactionModel(transaction.get());
		
		return null;
	}

	public List<TransactionModel> getTransactionByCustomerId(Integer cId) {
		List<Transaction> transactions = tRepo.findByCustomerId(cId);
		if(!transactions.isEmpty() && transactions!= null) {
			return transactions.stream().map(a -> EntityToTransactionModel(a)).collect(Collectors.toList());
		}
			
		
		return null;
	}

}
