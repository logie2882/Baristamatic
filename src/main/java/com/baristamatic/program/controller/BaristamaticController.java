package com.baristamatic.program.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baristamatic.program.dto.response.DrinkDetailResponse;
import com.baristamatic.program.dto.response.InventoryDetailsResponse;
import com.baristamatic.program.dto.response.ItemMenuDetailResponse;
import com.baristamatic.program.exception.BaristamaticException;
import com.baristamatic.program.service.BaristamaticService;
import com.baristamatic.program.utils.BaristamaticExceptionEnum;

@RestController
@RequestMapping(value="/coffeApp/baristamatic/v1")
@EnableAutoConfiguration
public class BaristamaticController {
	
	@Autowired
	BaristamaticService baristamaticService;
	
	
	
    @GetMapping(value = "/ping" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> ping(@RequestHeader HttpHeaders httpHeaders){
   		return ResponseEntity.status(HttpStatus.OK).body("*** Test for Baristamatic Application ***");
	}


	@GetMapping(value = "/inventory", produces = { MediaType.APPLICATION_JSON_VALUE })
	public <T> ResponseEntity<List<InventoryDetailsResponse>> getDefaultInventory(
			@RequestHeader HttpHeaders httpHeaders) {

		List<InventoryDetailsResponse> inventoryDetailsResponses = null;
		try {

			inventoryDetailsResponses = baristamaticService.retrieveInventoryDetails();

		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		return new ResponseEntity<>(inventoryDetailsResponses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/restockInventory", produces = { MediaType.APPLICATION_JSON_VALUE })
	public <T> ResponseEntity<List<InventoryDetailsResponse>> getRestockInventoryDetails(
			@RequestHeader HttpHeaders httpHeaders) {

		List<InventoryDetailsResponse> inventoryDetailsResponses = null;
		try {

			inventoryDetailsResponses = baristamaticService.retrieveRestockInventoryDetails();

		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		return new ResponseEntity<>(inventoryDetailsResponses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/drinkMenu", produces = { MediaType.APPLICATION_JSON_VALUE })
	public <T> ResponseEntity<List<ItemMenuDetailResponse>> getMenuDetails(
			@RequestHeader HttpHeaders httpHeaders) {

		List<ItemMenuDetailResponse>   itemMenuDetailResponses = null;
		try {

			itemMenuDetailResponses = baristamaticService.retrieveDrinkMenu();

		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		return new ResponseEntity<>(itemMenuDetailResponses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/drinkMenu/drinkRequested", produces = { MediaType.APPLICATION_JSON_VALUE })
	public <T> ResponseEntity<DrinkDetailResponse> getDrinkDetails(
			@RequestHeader HttpHeaders httpHeaders , @Valid @NotNull @RequestHeader(name = "drink_Id") Integer drink_id) {

		DrinkDetailResponse   drinkDetailResponse = null;
		try {

			drinkDetailResponse = baristamaticService.retrieveDrinkDetails(drink_id);

		} catch (Exception ex) {
			throw new BaristamaticException(BaristamaticExceptionEnum.BARISTAMATIC_DOWNSTREAM_SYSTEM_EXCEPTION);
		}

		return new ResponseEntity<>(drinkDetailResponse, HttpStatus.OK);
	}

}
