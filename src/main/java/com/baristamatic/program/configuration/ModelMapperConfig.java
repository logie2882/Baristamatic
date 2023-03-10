package com.baristamatic.program.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Configuration;

import com.baristamatic.program.dto.model.drink.Ingredient;
import com.baristamatic.program.entity.DrinkEntity;



@Configuration
public class ModelMapperConfig {
	
	public ModelMapperConfig(ModelMapper modelMapper) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		modelMapper.typeMap(DrinkEntity.class, Ingredient.class).addMappings(mapper  -> {
			mapper.map(DrinkEntity :: getCoffee , Ingredient :: setCoffee);
			mapper.map(DrinkEntity :: getDcafe_coffee , Ingredient :: setDecaf_coffee);
			mapper.map(DrinkEntity :: getSugar , Ingredient :: setSugar);
			mapper.map(DrinkEntity :: getCream , Ingredient :: setCream);
			mapper.map(DrinkEntity :: getSteamed_milk , Ingredient :: setSteamed_milk);
			mapper.map(DrinkEntity :: getFoamed_milk , Ingredient :: setFoamed_milk);
			mapper.map(DrinkEntity :: getEspresso, Ingredient :: setEspresso);
			mapper.map(DrinkEntity :: getCocoa , Ingredient :: setCocoa);
			mapper.map(DrinkEntity :: getWhipped_cream , Ingredient :: setWhipped_cream);
		});
		
	}

}
