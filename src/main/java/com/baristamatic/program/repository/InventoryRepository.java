package com.baristamatic.program.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baristamatic.program.entity.IngredientInventoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<IngredientInventoryEntity, Integer>,JpaSpecificationExecutor<IngredientInventoryEntity> {
	
	public IngredientInventoryEntity findByIngredientName(String ingredientName);

}
