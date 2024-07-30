package com.retailer.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  
 * Model class represents a RewardPointsModel 
 * 
 * RewardPointsModel gives the result of total Points and Monthly points
 * 
 * Here we using Lombok for @Data, @AllArgsConstructor, @NoArgsConstructor.
 *  so no need to add constructor, getters and setters.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardPointsModel {
	
	private Map<String , Integer> monthlyPoints;
	private Integer totalPoints;
	

}
