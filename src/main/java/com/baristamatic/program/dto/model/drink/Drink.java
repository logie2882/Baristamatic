package com.baristamatic.program.dto.model.drink;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Drink {

	private Integer drinkId;
	
	private String drinkName;
	
	private String drinkCost;
	
	private boolean availability;

	public Drink(Integer drinkId, String drinkName, String drinkCost, boolean availability) {
		// TODO Auto-generated constructor stub
		super();
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkCost = drinkCost;
		this.availability = availability;

	}
	
}
