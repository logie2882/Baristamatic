package com.baristamatic.program.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.baristamatic.program.dto.model.drink.Ingredient;
import com.baristamatic.program.entity.DrinkEntity;
import com.baristamatic.program.entity.IngredientInventoryEntity;
import com.baristamatic.program.repository.InventoryRepository;
import com.baristamatic.program.repository.ItemMenuRepository;
import com.baristamatic.program.utils.ModelMapperUtility;

@RunWith(MockitoJUnitRunner.class)
public class BaristamaticBuisnessServiceTest {

	@Mock
	ModelMapperUtility mapperUtility;
	@InjectMocks
	BaristamaticBusinessService baristamaticBusinessService;

	@Mock
	InventoryRepository inventoryRepository;
	
	@Mock
	ItemMenuRepository itemMenuRepository;
	

	@Test
	public void testCalculateDrinkPrice() {

		String drink = "coffee";
		Map<String, Integer> drinkIngredientMap = new HashMap<>();
		drinkIngredientMap.put("coffee", 3);
		drinkIngredientMap.put("sugar", 1);
		drinkIngredientMap.put("cream", 1);
		


		
		//Optional<>
		DrinkEntity drinkEntity = new DrinkEntity();
		drinkEntity.setDrinkNumber(1);
		drinkEntity.setDrinkName("coffee");
		drinkEntity.setCoffee(0);
		drinkEntity.setDcafe_coffee(0);
		drinkEntity.setCream(1);
		drinkEntity.setSugar(1);
		drinkEntity.setSteamed_milk(0);
		drinkEntity.setFoamed_milk(0);
		drinkEntity.setEspresso(0);
		drinkEntity.setCocoa(0);;
		drinkEntity.setWhipped_cream(0);;
		
		Ingredient ingredient = new Ingredient();
		
		ingredient.setCoffee(3);
		ingredient.setDecaf_coffee(0);
		ingredient.setCream(1);
		ingredient.setSugar(1);
		ingredient.setSteamed_milk(0);
		ingredient.setFoamed_milk(0);
		ingredient.setEspresso(0);
		ingredient.setCocoa(0);;
		ingredient.setWhipped_cream(0);;
		
		when(itemMenuRepository.findByDrinkName("coffee")).thenReturn(Optional.of(drinkEntity));
		when(baristamaticBusinessService.getDrinksIngredientMap(drink)).thenReturn(drinkIngredientMap);
		when(mapperUtility.convert(drinkEntity, Ingredient.class)).thenReturn(ingredient);
		when(inventoryRepository.findAll()).thenReturn(buildInventoryList());
		
		Double drinkPrice = baristamaticBusinessService.calculateDrinkPrice(drink);

		assertThat(drinkPrice).isEqualTo(2.75);

		
	}

	private static List<IngredientInventoryEntity> buildInventoryList() {

		IngredientInventoryEntity coffeeIngre = new IngredientInventoryEntity();
		coffeeIngre.setId(1);
		coffeeIngre.setIngredientName("coffee");
		coffeeIngre.setUnitCost(0.75);
		coffeeIngre.setAvailability(8);


		IngredientInventoryEntity decafCoffeeIngre = new IngredientInventoryEntity();
		decafCoffeeIngre.setId(2);
		decafCoffeeIngre.setIngredientName("decaf_coffee");
		decafCoffeeIngre.setUnitCost(0.75);
		decafCoffeeIngre.setAvailability(10);


		IngredientInventoryEntity sugarIngre = new IngredientInventoryEntity();
		sugarIngre.setId(3);
		sugarIngre.setIngredientName("sugar");
		sugarIngre.setUnitCost(0.25);
		sugarIngre.setAvailability(10);

		IngredientInventoryEntity creamIngre = new IngredientInventoryEntity();
		creamIngre.setId(4);
		creamIngre.setIngredientName("cream");
		creamIngre.setUnitCost(0.25);
		creamIngre.setAvailability(10);

		IngredientInventoryEntity steamedMilkIngre = new IngredientInventoryEntity();
		steamedMilkIngre.setId(5);
		steamedMilkIngre.setIngredientName("steamed_milk");
		steamedMilkIngre.setUnitCost(0.25);
		steamedMilkIngre.setAvailability(10);


		IngredientInventoryEntity foamedMilkIngre = new IngredientInventoryEntity();
		foamedMilkIngre.setId(6);
		foamedMilkIngre.setIngredientName("foamed_milk");
		foamedMilkIngre.setUnitCost(0.25);
		foamedMilkIngre.setAvailability(10);


		IngredientInventoryEntity espressoIngre = new IngredientInventoryEntity();
		espressoIngre.setId(7);
		espressoIngre.setIngredientName("espresso");
		espressoIngre.setUnitCost(0.75);
		espressoIngre.setAvailability(10);


		IngredientInventoryEntity cocoaIngre = new IngredientInventoryEntity();
		cocoaIngre.setId(8);
		cocoaIngre.setIngredientName("cocoa");
		cocoaIngre.setUnitCost(0.25);
		cocoaIngre.setAvailability(10);


		IngredientInventoryEntity whippedCreamIngre = new IngredientInventoryEntity();
		whippedCreamIngre.setId(9);
		whippedCreamIngre.setIngredientName("whipped_cream");
		whippedCreamIngre.setUnitCost(0.25);
		whippedCreamIngre.setAvailability(10);

		List<IngredientInventoryEntity> currentInventoryList = new ArrayList<>();

		currentInventoryList.add(whippedCreamIngre);
		currentInventoryList.add(cocoaIngre);
		currentInventoryList.add(espressoIngre);
		currentInventoryList.add(foamedMilkIngre);
		currentInventoryList.add(steamedMilkIngre);
		currentInventoryList.add(creamIngre);
		currentInventoryList.add(sugarIngre);
		currentInventoryList.add(decafCoffeeIngre);
		currentInventoryList.add(coffeeIngre);
		return currentInventoryList;
	}
	
}
