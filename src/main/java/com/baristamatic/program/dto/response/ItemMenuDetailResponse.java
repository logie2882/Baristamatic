package com.baristamatic.program.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemMenuDetailResponse {
	
	private Integer drinkId;
	
	private String drinkName;
	
	private String drinkCost;
	
	private boolean availability;

}
