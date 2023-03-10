package com.baristamatic.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baristamatic.program.entity.IngredientCostEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCostRepository extends JpaRepository<IngredientCostEntity, Integer>,JpaSpecificationExecutor<IngredientCostEntity>{

}
