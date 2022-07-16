package com.bk.shop4.demo.Service;

import java.util.Date;
import java.util.List;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.InnerDomain.InnerCargoData;



public interface ICargoService {
	Cargo create(Cargo c);

	void delete(String uuid);

	Cargo findByUuid(String uuid);

	List<Cargo> findAll();
	
	Cargo CreateInitial(InnerCargoData cargo);
	List<Drink>GetDrinksByDateRange(String uuid,Date start,Date end);
}
