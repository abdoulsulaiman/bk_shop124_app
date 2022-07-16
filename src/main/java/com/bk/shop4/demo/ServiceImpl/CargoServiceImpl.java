package com.bk.shop4.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.ClientOrder;
import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.Domain.OrderItem;
import com.bk.shop4.demo.InnerDomain.InnerCargoData;
import com.bk.shop4.demo.Repository.CargoRepo;
import com.bk.shop4.demo.Repository.ClientOrderRepo;
import com.bk.shop4.demo.Repository.OrderItemRepo;
import com.bk.shop4.demo.Service.ICargoService;
import com.bk.shop4.demo.Utility.ObjectNotFoundException;

@Service
public class CargoServiceImpl implements ICargoService {
	
	@Autowired
	CargoRepo crepo;
	
	@Autowired
	ClientOrderRepo orderRepo;
	
	@Autowired
	OrderItemRepo itemrepo;

	@Override
	public Cargo create(Cargo c) {
		
		return crepo.save(c);
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Cargo> c = crepo.findByUuidAndDeletedStatus(uuid, false);
			if (c.isPresent()) {
				Cargo cargo = c.get();
				cargo.setDeletedStatus(true);
				this.create(cargo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Cargo findByUuid(String uuid) {
		Optional<Cargo> cl = crepo.findByUuidAndDeletedStatus(uuid, false);
        if (cl.isPresent()) {
            return cl.get();
        } else {
            throw new ObjectNotFoundException("Cargo not found!");
        }
	}

	@Override
	public List<Cargo> findAll() {
		
		return crepo.findByDeletedStatus(false);
	}

	@Override
	public Cargo CreateInitial(InnerCargoData cargo) {
		Cargo c=new Cargo();
		c.setNames(cargo.getNames());
		c.setEmail(cargo.getEmail());
		c.setPhone(cargo.getPhone());
		c.setAddress(cargo.getAddress());
		c.setLatitude(cargo.getLatitude());
		c.setLongitude(cargo.getLongitude());
		crepo.save(c);
		return c;
	}

	@Override
	public List<Drink> GetDrinksByDateRange(String uuid,Date start,Date end) {
		 List<ClientOrder>orders=orderRepo.findOrderByCargoAndTimeFrame(uuid, start, end);
		 List<OrderItem>items =new ArrayList<OrderItem>();
		 List<Drink>drinks=new ArrayList<Drink>();
		 for(ClientOrder order:orders) {
			 List<OrderItem> item=itemrepo.findByClientOrderUuidAndDeletedStatus(order.getUuid(), false);
			 items.addAll(item);
		 }
		 for(OrderItem i :items) {
			 drinks.add(i.getDrink());
		 }
		return drinks.stream().distinct().collect(Collectors.toList());
	}

}
