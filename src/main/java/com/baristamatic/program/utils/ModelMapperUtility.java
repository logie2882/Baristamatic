package com.baristamatic.program.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ModelMapperUtility {

	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * Utility Method to achieve Straight forward conversion b/w 2 Object type
	 * Use this method instead of using modelMapper Map method.
	 */
	public<S,D> D convert(S s, Type destinationType) {
		
		Assert.notNull(s,"source");
		Assert.notNull(destinationType,"destinationType");
		Type type = TypeToken.<D>of(destinationType).getType();
		
		return modelMapper.map(s, type);
	}
	
	
	/*
	 * Utility Method to achieve Straight forward conversion b/w 2 list of Object with different types contained in a list
	 * Use this method instead of using modelMapper Map method.
	 */
	public<S,D> List<D> convertList(List<S> source, Class<D> destClass) {
			
			Assert.notNull(source,"source");
			
			return source.stream().map(entity -> modelMapper.map(entity, destClass)).collect(Collectors.toList());
		}
	 
}
