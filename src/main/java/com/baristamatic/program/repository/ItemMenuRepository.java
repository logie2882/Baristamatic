package com.baristamatic.program.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baristamatic.program.entity.DrinkEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuRepository extends JpaRepository<DrinkEntity, Integer>,JpaSpecificationExecutor<DrinkEntity> {
	
		public Optional<DrinkEntity> findByDrinkName(String Drink);
	
}
