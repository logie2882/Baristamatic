package com.baristamatic.program.service;

import java.util.List;

import com.baristamatic.program.dto.response.DrinkDetailResponse;
import com.baristamatic.program.dto.response.InventoryDetailsResponse;
import com.baristamatic.program.dto.response.ItemMenuDetailResponse;
import org.springframework.stereotype.Service;


@Service
public interface BaristamaticService {


	List<InventoryDetailsResponse>  retrieveInventoryDetails();

	List<InventoryDetailsResponse> retrieveRestockInventoryDetails();

	List<ItemMenuDetailResponse> retrieveDrinkMenu();

	DrinkDetailResponse retrieveDrinkDetails(Integer drink_id);
	
}
