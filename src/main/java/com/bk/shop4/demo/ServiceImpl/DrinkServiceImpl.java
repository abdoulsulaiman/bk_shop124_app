package com.bk.shop4.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.Domain.ClientOrder;
import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.Domain.DrinkAvaibility;
import com.bk.shop4.demo.Domain.OrderItem;
import com.bk.shop4.demo.InnerDomain.InnerConsumedDrink;
import com.bk.shop4.demo.InnerDomain.InnerDrinkData;
import com.bk.shop4.demo.Repository.CargoRepo;
import com.bk.shop4.demo.Repository.ClientOrderRepo;
import com.bk.shop4.demo.Repository.ClientRepo;
import com.bk.shop4.demo.Repository.DrinkRepo;
import com.bk.shop4.demo.Repository.OrderItemRepo;
import com.bk.shop4.demo.Service.IDrinkService;
import com.bk.shop4.demo.Utility.ObjectNotFoundException;




@Service
public class DrinkServiceImpl implements IDrinkService {
	
	@Autowired
	DrinkRepo drepo;
	
	@Autowired
	ClientOrderRepo orderrepo;
	
	@Autowired
	OrderItemRepo itemrepo;
	
	@Autowired
	ClientRepo crepo;
	
	@Autowired
	CargoRepo cargorepo;

	@Override
	public Drink create(Drink drink) {
		
		return drepo.save(drink);
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Drink> d = drepo.findByUuidAndDeletedStatus(uuid, false);
			if (d.isPresent()) {
				Drink drink = d.get();
				drink.setDeletedStatus(true);
				this.create(drink);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Drink findByUuid(String uuid) {
		Optional<Drink> d = drepo.findByUuidAndDeletedStatus(uuid, false);
        if (d.isPresent()) {
            return d.get();
        } else {
            throw new ObjectNotFoundException("Drink not found!");
        }
	}

	@Override
	public List<Drink> findAll() {
		
		return drepo.findByDeletedStatus(false);
	}

	@Override
	public Drink CreateInitial(InnerDrinkData drink) {
		Drink d =new Drink();
		d.setName(drink.getName());
		d.setPrice(drink.getPrice());
		d.setDescription(drink.getDescription());
		d.setQuantity(drink.getQuantity());
		d.setAvailability(DrinkAvaibility.Available);
		drepo.save(d);
		return d;
	}

	@Override
	public List<InnerConsumedDrink> getMostConsumedDrink() {
		List<ClientOrder>orders=orderrepo.findAllByDeletedStatus(false);
		 List<OrderItem>items =new ArrayList<OrderItem>();
		 List<Drink>all_drinks=drepo.findByDeletedStatus(false);
		 List<InnerConsumedDrink>drinks=new ArrayList<InnerConsumedDrink>();
		 List<InnerConsumedDrink>return_list=new ArrayList<InnerConsumedDrink>();
		 
		 for(ClientOrder order:orders) {
			 List<OrderItem> item=itemrepo.findByClientOrderUuidAndDeletedStatus(order.getUuid(), false);
			 items.addAll(item);
		 }
		
		 for(Drink d:all_drinks) {
			 int q=0;
		 for(OrderItem i :items) {
			 if(d.getId()==i.getDrink().getId()) {
				 q+=1;
				 
			 } 
		 }
		 InnerConsumedDrink cons=new InnerConsumedDrink();
		  cons.setDrink(d);
		  cons.setQuantity(q);
		 drinks.add(cons);
		 }
		 int max=drinks.get(0).getQuantity();
		 int min=drinks.get(0).getQuantity();
		 for(InnerConsumedDrink cd:drinks) {
			 if(cd.getQuantity()>max) {
				 max=cd.getQuantity();
			 }
			 if(cd.getQuantity()<min) {
				 min=cd.getQuantity();
			 }
		 }
		 
		 int average=(max+min)/2;
		 
		 for(InnerConsumedDrink dr:drinks) {
			 if(dr.getQuantity()>average) {
				 return_list.add(dr);
			 }
		 }
		 
		 
		return return_list;
		 
	}
	
	private double Calculatedistance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		    dist = dist * 1.609344;

		  return (dist);

		}




		private double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);

		}


		private double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
		}

		@Override
		public Map<String, Object> GetAllDrinkWithNearestCargo(String uuid) {
			Map<String,Object> map=new HashMap<>();
			Client client=crepo.findByUuidAndDeletedStatus(uuid, false).get();
			List<Cargo>cargo_list=new ArrayList<Cargo>();
			for (Cargo c:cargorepo.findByDeletedStatus(false)) {
				if(Calculatedistance(Double.parseDouble(client.getLatitude()), Double.parseDouble(client.getLongitude()), Double.parseDouble(c.getLatitude()), Double.parseDouble(c.getLongitude()))<3) {
					cargo_list.add(c);
				}
			}
			map.put("cargoes", cargo_list);
			map.put("drinks", drepo.findByDeletedStatus(false));
			return map;
		}


}
