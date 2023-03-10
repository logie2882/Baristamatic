package com.baristamatic.program.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import com.baristamatic.program.dto.model.drink.Drink;
import com.baristamatic.program.dto.response.DrinkDetailResponse;
import com.baristamatic.program.dto.response.InventoryDetailsResponse;
import com.baristamatic.program.dto.response.ItemMenuDetailResponse;
import com.baristamatic.program.entity.DrinkEntity;
import com.baristamatic.program.entity.IngredientInventoryEntity;
import com.baristamatic.program.exception.BaristamaticException;
import com.baristamatic.program.repository.InventoryRepository;
import com.baristamatic.program.repository.ItemMenuRepository;
import com.baristamatic.program.utils.ModelMapperUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class BaristamaticServiceImplTest {

	@Mock
	ModelMapperUtility mapperUtility;

	@Autowired
	ModelMapper modelMapper;
	@InjectMocks
	BaristamaticServiceImpl classToTest;
	
	@Mock
	InventoryRepository  inventoryRepository;
	
	@Mock
	ItemMenuRepository itemMenuRepository;
	
	@Mock
	BaristamaticBusinessService baristamaticBusinessService;
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		modelMapper = new ModelMapper();
		mapperUtility = new ModelMapperUtility();

	}
	
	@Test
	public void testRetrieveInventoryDetails() {
		//Mockito.when(mapperUtility.convertList(buildInventoryList(), InventoryDetailsResponse.class)).thenReturn(buildInventoryResponseList());
		Mockito.when(inventoryRepository.findAll()).thenReturn(buildInventoryList());
		List<InventoryDetailsResponse>  inventoryEntitiesList= classToTest.retrieveInventoryDetails();
		assertNotNull(inventoryEntitiesList);
	}
	
	@Test
	public void testRetrieveInventoryDetails_DBException() {
		Mockito.when(inventoryRepository.findAll()).thenThrow(BaristamaticException.class);
			try{
				List<InventoryDetailsResponse> inventoryEntitiesList = classToTest.retrieveInventoryDetails();
				fail();
			}catch(Exception ex) {
				
			}
			
	}
	
	@Test
	public void testRetrieveRestockInventoryDetails() {
		
		List<IngredientInventoryEntity> inventoryEntityList = null;
		List<InventoryDetailsResponse> inventoryDetails = null;
		List<IngredientInventoryEntity> defaultInventoryEntityList = new ArrayList<>();
		
		Mockito.when(inventoryRepository.findAll()).thenReturn(buildInventoryList());
		//Mockito.when(inventoryRepository.saveAll(buildInventoryList())).thenReturn(buildInventoryList());
		List<InventoryDetailsResponse>  inventoryEntitiesList= classToTest.retrieveInventoryDetails();
		assertNotNull(inventoryEntitiesList);
	}
	
	@Test
	public void testRetrieveDrinkMenu() {
		
		List<DrinkEntity> listDrinkEntity = getDrinkList();
		List<ItemMenuDetailResponse> itemMenuDetailResponses = null;
		
		Mockito.when(itemMenuRepository.findAll()).thenReturn(listDrinkEntity);
		
		itemMenuDetailResponses  = classToTest.retrieveDrinkMenu();
		assertNotNull(itemMenuDetailResponses);
	}
	
	//@Test
	//@Ignore
	public void testRetrieveDrinkDetails() {
		
		DrinkEntity drinkEntity = new DrinkEntity();
		
		drinkEntity.setDrinkNumber(1);
		drinkEntity.setDrinkName("coffee");
		drinkEntity.setCoffee(10);
		drinkEntity.setDcafe_coffee(10);
		drinkEntity.setSugar(10);
		drinkEntity.setCream(10);
		drinkEntity.setFoamed_milk(10);
		drinkEntity.setSteamed_milk(10);
		drinkEntity.setCocoa(10);
		drinkEntity.setEspresso(10);
		drinkEntity.setWhipped_cream(10);
		
		List<IngredientInventoryEntity> currentInventory = buildInventoryList();
		
		Mockito.when(itemMenuRepository.findByDrinkName("coffee")).thenReturn(Optional.of(drinkEntity));
		Mockito.when(inventoryRepository.findAll()).thenReturn(currentInventory);
		
		DrinkDetailResponse detailResponse = classToTest.retrieveDrinkDetails(1);
		
		assertNotNull(detailResponse);
	}

	
	private List<DrinkEntity> getDrinkList() {
		
		List<DrinkEntity> drinkEntity01 = new ArrayList<>();
		DrinkEntity drinkEntity = new DrinkEntity();
		// TODO Auto-generated method stub
		
		drinkEntity.setCoffee(10);
		drinkEntity.setDcafe_coffee(10);
		drinkEntity.setSugar(10);
		drinkEntity.setCream(10);
		drinkEntity.setFoamed_milk(10);
		drinkEntity.setSteamed_milk(10);
		drinkEntity.setCocoa(10);
		drinkEntity.setEspresso(10);
		drinkEntity.setWhipped_cream(10);
		drinkEntity01.add(drinkEntity);
		
		return drinkEntity01;
	}

	private static List<IngredientInventoryEntity> buildInventoryList() {
		// TODO Auto-generated method stub
		List<IngredientInventoryEntity> ingridentList = new ArrayList<>();
		IngredientInventoryEntity entity00 = new IngredientInventoryEntity();
		IngredientInventoryEntity entity01 = new IngredientInventoryEntity();
		
		entity00.setId(1);
		entity00.setIngredientName("Coffee");
		entity00.setAvailability(10);
		entity00.setUnitCost(0.75);
		
		entity01.setId(9);
		entity01.setIngredientName("Whipped Cream");
		entity01.setAvailability(10);
		entity01.setUnitCost(1.0);
		
				ingridentList.add(entity00);
				ingridentList.add(entity01);
				
			return ingridentList;
	}
	
	private List<InventoryDetailsResponse> buildInventoryResponseList() {
		// TODO Auto-generated method stub
		List<InventoryDetailsResponse> ingridentResponseList = new ArrayList<>();
		InventoryDetailsResponse entityRes00 = new InventoryDetailsResponse();
		InventoryDetailsResponse entityRes01 = new InventoryDetailsResponse();
		
		entityRes00.setId(1);
		entityRes00.setIngredientName("Coffee");
		entityRes00.setAvailability(10);
		entityRes00.setUnitCost(0.75);
		
		entityRes01.setId(9);
		entityRes01.setIngredientName("Whipped Cream");
		entityRes01.setAvailability(10);
		entityRes01.setUnitCost(1.0);
		
		ingridentResponseList.add(entityRes00);
		ingridentResponseList.add(entityRes01);
				
			return ingridentResponseList;
	}
	
	
	

}
