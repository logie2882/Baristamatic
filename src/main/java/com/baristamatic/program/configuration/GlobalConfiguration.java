package com.baristamatic.program.configuration;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class GlobalConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	@SuppressWarnings("unchecked")
	@Bean("baristaProperties")
	public Map<String, String> barista() {
		Map<String, String> barista = new HashMap<>();
		try {

			String baristaJson = System.getenv("BARISTA_APPLICATION");

			if (null != baristaJson && baristaJson.length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				barista = mapper.readValue(baristaJson, Map.class);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return barista;
	}

}
