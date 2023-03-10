package com.baristamatic.program.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INGREDIENT_COST")
@Getter
@Setter
public class IngredientCostEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INGREDIENT_ID")
    private Integer id;
	
	@Column(name = "INGREDIENT_NAME")
	private String ingredientName;
	
	@Column(name = "UNIT_COST")
	private double unitCost;

}
