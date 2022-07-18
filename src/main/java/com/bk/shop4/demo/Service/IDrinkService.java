package com.bk.shop4.demo.Service;

import java.util.List;
import java.util.Map;

import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.InnerDomain.InnerConsumedDrink;
import com.bk.shop4.demo.InnerDomain.InnerDrinkData;



public interface IDrinkService {
	
	Drink create(Drink drink);

	void delete(String uuid);

	Drink findByUuid(String uuid);

	List<Drink> findAll();
	
	Drink CreateInitial(InnerDrinkData drink);
	
	List<InnerConsumedDrink>getMostConsumedDrink();
	
	Map<String,Object> GetAllDrinkWithNearestCargo(String uuid);
	
	Drink getDrinkByName(String name);

}
