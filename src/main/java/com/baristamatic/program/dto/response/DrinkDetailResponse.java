package com.baristamatic.program.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DrinkDetailResponse {

	private Integer drinkId;
	
	private String drinkName;
	
	private String price;
	
	private boolean isAvailable;
}
