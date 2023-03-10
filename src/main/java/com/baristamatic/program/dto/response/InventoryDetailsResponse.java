package com.baristamatic.program.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDetailsResponse {
	
    private Integer id;
	
	private String ingredientName;
	
	private Integer availability;
	
	private double unitCost;

}
