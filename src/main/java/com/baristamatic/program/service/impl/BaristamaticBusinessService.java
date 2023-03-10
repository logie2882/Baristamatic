package com.baristamatic.program.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baristamatic.program.dto.model.drink.Ingredient;
import com.baristamatic.program.entity.DrinkEntity;
import com.baristamatic.program.entity.IngredientInventoryEntity;
import com.baristamatic.program.repository.InventoryRepository;
import com.baristamatic.program.repository.ItemMenuRepository;
import com.baristamatic.program.utils.ModelMapperUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BaristamaticBusinessService {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Autowired
	ItemMenuRepository itemMenuRepository;
	
	@Autowired
	InventoryRepository  inventoryRepository;
	
	@Autowired
	ModelMapperUtility  mapperUtility ;

	boolean available = false;
	public Double calculateDrinkPrice(String drinkName) {
		// TODO Auto-generated method stub
		// 1. getIngridents for the drink.
		Map<String, Integer> drinkIngredientMap = this.getDrinksIngredientMap(drinkName);

		// 2.get Inventory details and price of each item.
		Map<String, Double> currentPriceMap = this.getDrinkPriceMap(drinkName);

		// 3. Iterate map and calculate price
		Map<String, Double> drinkPriceMap = new HashMap<>();

		drinkPriceMap = this.getPriceMap(drinkIngredientMap, currentPriceMap, drinkPriceMap);

		Iterator<Double> iterator = drinkPriceMap.values().iterator();
		double totalPrice = 0.0;
		while (iterator.hasNext()) {
			double price = 0.0;
			price = iterator.next();
			totalPrice += price;
		}
		return Double.parseDouble(df.format(totalPrice));
	}


	public boolean isInventoryAvailable(String drinkName) {
		// TODO Auto-generated method stub

		
		// 1. Get the list of ingredients needed for the drink
		Map<String, Integer> drinkIngredientMap = this.getDrinksIngredientMap(drinkName);

		// 2. Get current inventory
		Map<String, Integer> currentInventoryMap = this.getCurrentInventoryMap(drinkName);

		// 3. Iterate keys and compare values from both map and determine availability
		Map<String, Integer> updatedInventoryMap = new HashMap<>();
		updatedInventoryMap = this.getUpdatedInventoryMap(drinkIngredientMap, currentInventoryMap, updatedInventoryMap);

		Iterator<Integer> iterator = updatedInventoryMap.values().iterator();
		while (iterator.hasNext()) {
			Integer value = iterator.next();
			if (value < 0) {
				return available;
			}
		}

			//Update the Inventory after the dispense the drink
			List<IngredientInventoryEntity> listInventoryAfterDrink = inventoryRepository.findAll();
			Map<String, Integer> finalInventoryMap = new HashMap<>(updatedInventoryMap);
			listInventoryAfterDrink.stream().forEach(ingredient -> {
				ingredient.setAvailability(finalInventoryMap.get(ingredient.getIngredientName()));
			});
			available = true;
			inventoryRepository.saveAll(listInventoryAfterDrink);
		
		return available;
	}
	
	
	private Map<String, Integer> getUpdatedInventoryMap(Map<String, Integer> drinkIngredientMap,
			Map<String, Integer> currentInventoryMap, Map<String, Integer> updatedInventoryMap) {
		// TODO Auto-generated method stub
		drinkIngredientMap.entrySet().stream().forEach(entry -> {
			updatedInventoryMap.put(entry.getKey(), currentInventoryMap.get(entry.getKey()) - entry.getValue());
		});
		return updatedInventoryMap;
	}


	private Map<String, Integer> getCurrentInventoryMap(String drinkName) {
		// TODO Auto-generated method stub
		List<IngredientInventoryEntity> currentInventory = inventoryRepository.findAll();
		return currentInventory.stream().collect(
				Collectors.toMap(IngredientInventoryEntity::getIngredientName, IngredientInventoryEntity::getAvailability));
	}


	private Map<String, Double> getDrinkPriceMap(String drinkName) {
		// TODO Auto-generated method stub
		List<IngredientInventoryEntity> currentInventory = inventoryRepository.findAll();
		return currentInventory.stream()
				.collect(Collectors.toMap(IngredientInventoryEntity::getIngredientName, IngredientInventoryEntity::getUnitCost));
	}
	
	private Map<String, Double> getPriceMap(Map<String, Integer> drinkIngredientMap,
			Map<String, Double> currentPriceMap, Map<String, Double> drinkPriceMap) {
		// TODO Auto-generated method stub
		
		drinkIngredientMap.entrySet().stream().forEach(entry -> {
			drinkPriceMap.put(entry.getKey(), currentPriceMap.get(entry.getKey()) * entry.getValue());
		});
		
		return drinkPriceMap;
	}
	
	Map<String , Integer> getDrinksIngredientMap (String drinkName){
		
				DrinkEntity drinkEntity = itemMenuRepository.findByDrinkName(drinkName).get();
				Ingredient ingredient = mapperUtility.convert(drinkEntity, Ingredient.class );
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.convertValue(ingredient, new TypeReference<Map<String, Integer>>() {
				});
				
	}

}
