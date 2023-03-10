package com.baristamatic.program.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baristamatic.program.dto.model.drink.Drink;
import com.baristamatic.program.dto.response.DrinkDetailResponse;
import com.baristamatic.program.dto.response.InventoryDetailsResponse;
import com.baristamatic.program.dto.response.ItemMenuDetailResponse;
import com.baristamatic.program.entity.DrinkEntity;
import com.baristamatic.program.entity.IngredientInventoryEntity;
import com.baristamatic.program.exception.BaristamaticException;
import com.baristamatic.program.repository.InventoryRepository;
import com.baristamatic.program.repository.ItemMenuRepository;
import com.baristamatic.program.service.BaristamaticService;
import com.baristamatic.program.utils.BaristamaticConstant;
import com.baristamatic.program.utils.BaristamaticDrinkEnum;
import com.baristamatic.program.utils.BaristamaticExceptionEnum;
import com.baristamatic.program.utils.ModelMapperUtility;

@Service
public class BaristamaticServiceImpl implements BaristamaticService{
	
	@Autowired
	InventoryRepository  inventoryRepository;
	
	@Autowired
	ItemMenuRepository itemMenuRepository;
	
	@Autowired
	BaristamaticBusinessService baristamaticBusinessService;
	
	@Autowired
	ModelMapperUtility modelMapperUtility;
	

	
	

	@Override
	public List<InventoryDetailsResponse> retrieveInventoryDetails() {
		// TODO Auto-generated method stub
		List<IngredientInventoryEntity> inventoryEntityList = null;
		List<InventoryDetailsResponse> inventoryDetails = null;

		try {
			inventoryEntityList = inventoryRepository.findAll();
		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		inventoryDetails = modelMapperUtility.convertList(inventoryEntityList, InventoryDetailsResponse.class);

		return inventoryDetails;
	}

	@Override
	public List<InventoryDetailsResponse> retrieveRestockInventoryDetails() {
		// TODO Auto-generated method stub
		
		List<IngredientInventoryEntity> inventoryEntityList = null;
		List<InventoryDetailsResponse> inventoryDetails = null;
		List<IngredientInventoryEntity> defaultInventoryEntityList = null;

		try {
			inventoryEntityList = inventoryRepository.findAll();
			
			inventoryEntityList.stream().forEach(items -> {
				items.setAvailability(BaristamaticConstant.DEFAULT_INGREDIENET_VALUE);
			});
			
			defaultInventoryEntityList = inventoryRepository.saveAll(inventoryEntityList);
			
		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		inventoryDetails = modelMapperUtility.convertList(defaultInventoryEntityList, InventoryDetailsResponse.class);

		return inventoryDetails;
	}

	@Override
	public List<ItemMenuDetailResponse> retrieveDrinkMenu() {
		// TODO Auto-generated method stub
		List<DrinkEntity> listDrinkEntity = null;
		List<Drink> drinkLst = new ArrayList<>();
		List<ItemMenuDetailResponse> itemMenuDetailResponses = null;
		
		try{
			listDrinkEntity = 		itemMenuRepository.findAll();
			
			listDrinkEntity.forEach(
					ingredinet -> drinkLst.add(new Drink(ingredinet.getDrinkNumber(), 
																					ingredinet.getDrinkName(),
																					BaristamaticConstant.DOLLAR_SIGN + String.valueOf(
																							baristamaticBusinessService.calculateDrinkPrice(ingredinet.getDrinkName())),
																					baristamaticBusinessService.isInventoryAvailable(ingredinet.getDrinkName())
																					)));

		}catch(Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}
		
		itemMenuDetailResponses = modelMapperUtility.convertList(drinkLst,ItemMenuDetailResponse.class);
		
		return itemMenuDetailResponses;
	}

	@Override
	public DrinkDetailResponse retrieveDrinkDetails(Integer drink_Id) {
		
		// TODO Auto-generated method stub
		DrinkDetailResponse drinkDetailResponse = new DrinkDetailResponse();
		
		String requestedDrinkName = BaristamaticDrinkEnum.getDrinkName(drink_Id);
		
		if (requestedDrinkName != null) {
			try {
				drinkDetailResponse.setAvailable(baristamaticBusinessService.isInventoryAvailable(requestedDrinkName));
				
				if (drinkDetailResponse.isAvailable()) {
					drinkDetailResponse.setPrice(BaristamaticConstant.DOLLAR_SIGN
							+ String.valueOf(baristamaticBusinessService.calculateDrinkPrice(requestedDrinkName)));
					drinkDetailResponse.setDrinkName(requestedDrinkName);
				}else {
					throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_INVALID_INVENTORY);
				}
			}catch(BaristamaticException ex) {
				throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
			}
			
		}else {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_INVALID_REQUEST);
		}

		return drinkDetailResponse;
	}
	

}
