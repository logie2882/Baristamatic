package com.baristamatic.program.utils;

public enum BaristamaticDrinkEnum {
	
	COFFEE(1),
	
	DECAF_COFFEE(2),
	
	CAFFE_LATTE(3), 
	
	CAFFE_AMERICANO(4),
	
	CAFFE_MOCHA(5),
	
	CAPPUCCINO(6);
	
	private int drinkCode;
	
	
	private BaristamaticDrinkEnum(int drinkCode) {
		// TODO Auto-generated constructor stub
		this.drinkCode = drinkCode;
	}
	

	public int getDrinkCode() {
		return drinkCode;
	}
	
	
	public static String getDrinkName(int drink_Id) {
		
		switch(drink_Id) {
								case 1: 
									return  BaristamaticConstant.COFFEE;
								case 2:
									return  BaristamaticConstant.DECAF_COFFEE;
								case 3:
									return BaristamaticConstant.CAFFE_LATTE;
								case 4:
									return BaristamaticConstant.CAFFE_AMERICANO;
								case 5:
									return BaristamaticConstant.CAFFE_MOCHA;
								case 6:
									return BaristamaticConstant.CAPPUCCINO;
								default:
									return null;
		}
		
	}

}
