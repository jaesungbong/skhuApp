package com.example.skhuapp.food;


public class FoodManager {
	
	private static FoodManager instance;
	public static FoodManager getInstance() {
		if (instance == null) {
			instance = new FoodManager();
		}
		return instance;
	}
	
	private FoodManager() {}
	
	private int foodCode=0;
	
	public void setFoodCode(int code){
		foodCode=code;
	}
	
	public int getFoodCode(){
		return this.foodCode;
	}

}
