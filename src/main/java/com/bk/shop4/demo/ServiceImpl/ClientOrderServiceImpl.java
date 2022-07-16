package com.bk.shop4.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.Domain.ClientOrder;
import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.Domain.DrinkAvaibility;
import com.bk.shop4.demo.Domain.OrderItem;
import com.bk.shop4.demo.Domain.OrderStatus;
import com.bk.shop4.demo.Domain.Receipt;
import com.bk.shop4.demo.InnerDomain.DrinkData;
import com.bk.shop4.demo.InnerDomain.InnerClientTopFiveOrder;
import com.bk.shop4.demo.InnerDomain.InnerCompleteOrder;
import com.bk.shop4.demo.InnerDomain.InnerOrderData;
import com.bk.shop4.demo.Repository.CargoRepo;
import com.bk.shop4.demo.Repository.ClientOrderRepo;
import com.bk.shop4.demo.Repository.ClientRepo;
import com.bk.shop4.demo.Repository.DrinkRepo;
import com.bk.shop4.demo.Repository.OrderItemRepo;
import com.bk.shop4.demo.Repository.ReceiptRepo;
import com.bk.shop4.demo.Service.IClientOrderService;
import com.bk.shop4.demo.Utility.ObjectNotFoundException;

@Service
public class ClientOrderServiceImpl implements IClientOrderService {
	
	
	@Autowired
	ClientOrderRepo orderRepo;
	
	@Autowired
	DrinkRepo drepo;
	
	@Autowired
	ClientRepo crepo;
	
	@Autowired
	OrderItemRepo itemrepo;
	
	@Autowired
	CargoRepo cargorepo;
	
	@Autowired
	ReceiptRepo receiptRepo;

	@Override
	public ClientOrder create(ClientOrder c) {
		
		return orderRepo.save(c);
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<ClientOrder> order = orderRepo.findByUuidAndDeletedStatus(uuid, false);
			if (order.isPresent()) {
				ClientOrder cl = order.get();
				cl.setDeletedStatus(true);
				this.create(cl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public ClientOrder findByUuid(String uuid) {
		Optional<ClientOrder> order = orderRepo.findByUuidAndDeletedStatus(uuid, false);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new ObjectNotFoundException("ClientOrder not found!");
        }
	}

	@Override
	public List<ClientOrder> findByClient(String uuid) {
		
		return orderRepo.findByClientUuidAndDeletedStatus(uuid, false);
	}

	@Override
	public List<ClientOrder> findByCargo(String uuid) {
		
		return orderRepo.findByCargoUuidAndDeletedStatus(uuid, false);
	}

	@Override
	public List<ClientOrder> findAll() {
		
		return orderRepo.findAllByDeletedStatus(false);
	}

	@Override
	public String createInitialOrder(InnerOrderData data) {
		 ClientOrder order =new ClientOrder();
		 order.setClient(crepo.findByUuidAndDeletedStatus(data.getClientUuid(), false).get());
		 order.setOrderCode(generateOrderCode());
		 boolean check=false;
		 String drinkName="";
		 String msg="";
		try {
		
		 double totalPrice=0;
		 for (DrinkData d : data.getList()) {
			 Drink drink=drepo.findByUuidAndDeletedStatus(d.getDrinkUuid(), false).get(); 
			  if(drink!=null) {
				 if(drink.getQuantity()>d.getQuantity()) {
				 totalPrice+=(d.getQuantity()*drink.getPrice());
				 drink.setQuantity(drink.getQuantity()-d.getQuantity());
				 if(drink.getQuantity()==0) {
					 drink.setAvailability(DrinkAvaibility.Unavailable);
				 }
				 drepo.save(drink);
				 orderRepo.save(order);
				 OrderItem item=new OrderItem();
				 item.setClientOrder(order);
				 item.setDrink(drink);
				 item.setPrice(drink.getPrice());
				 item.setQuantity(d.getQuantity());
				 item.setTotalPrice(item.getPrice()*item.getQuantity());
				 itemrepo.save(item);
			 }else {
				  check=true;
				  drinkName=drink.getName();
			 }
			 }
		 }
		 
		 if(!check) {
			 order.setTotalPrice(totalPrice);
			 order.setStatus(OrderStatus.Pending);
			 orderRepo.save(order);
			 msg="Order Created Successfuly";
		 }else {
			 msg="Error Occured, Requested Quantity for "+drinkName+ " is not Available";
		 }
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	


@Override
public ClientOrder completeOrder(InnerCompleteOrder data) {
	  Optional <Cargo> c=cargorepo.findByUuidAndDeletedStatus(data.getCargoUuid(), false);
	  Optional <ClientOrder> o=orderRepo.findByUuidAndDeletedStatus(data.getOrderUuid(), false);
	  ClientOrder order=null;
	  if(c.isPresent() && o.isPresent()) {
		  Cargo cargo=c.get();
		  order=o.get();
		  order.setCargo(cargo);
		  order.setStatus(OrderStatus.Completed);
		  orderRepo.save(order);
		  
		  Receipt new_receipt=new Receipt();
		  new_receipt.setReceiptNumber(generateReceiptCode());
		  new_receipt.setOrder(order);
		  receiptRepo.save(new_receipt);
	  }else {
		  throw new ObjectNotFoundException("Error of Occurred , Not Data Found for Cargo and Order");
	  }
	  
	return order;
}


public  String generateOrderCode(){
	String code="";
	
	
	int size=orderRepo.findAllByDeletedStatus(false).size();
	
	
	if(size==0){
		code="CO-1";
	}else{
		
		long newCode=((size+1));
		
		code="CO-"+newCode; 
	}
	
	return code;
}

public  String generateReceiptCode(){
	String code="";
	
	
	int size=receiptRepo.findAllByDeletedStatus(false).size();
	
	
	if(size==0){
		code="RN-1";
	}else{
		
		long newCode=((size+1));
		
		code="RN-"+newCode; 
	}
	
	return code;
}

@Override
public List<ClientOrder> getMostPaidOrder() {
	List<ClientOrder>all_orders=orderRepo.findSortesAll();
	List<ClientOrder>list=new ArrayList<ClientOrder>();
	double max=all_orders.get(0).getTotalPrice();
	 double min=all_orders.get(0).getTotalPrice();
	 for(ClientOrder order:all_orders) {
		 if(order.getTotalPrice()>max) {
			 max=order.getTotalPrice();
		 }
		 if(order.getTotalPrice()<min) {
			 min=order.getTotalPrice();
		 }
	 }
	 
	 for(ClientOrder order:all_orders) {
		 if(order.getTotalPrice()>(max+min)/2) {
			 list.add(order);
		 }
	 }	
	 if(list.size()>10) {
	return list.subList(0, 10);
	 }else {
		 return list;
	 }
			
}

@Override
public List<InnerClientTopFiveOrder> getClientTopFiveOrder() {
	List<ClientOrder>all_orders=orderRepo.findSorteByDate();
	List<Client>clients=crepo.findByDeletedStatus(false);
	
	List<InnerClientTopFiveOrder>list=new ArrayList<InnerClientTopFiveOrder>();
	
	for(Client c:clients) {
		InnerClientTopFiveOrder data=new InnerClientTopFiveOrder();
		List<ClientOrder>new_list=new ArrayList<>();
		for(ClientOrder order:all_orders) {
			if(c.getId()==order.getClient().getId()) {
				new_list.add(order);
			}
		}
		data.setClient(c);
		data.setList(new_list.subList(0, 5));
		list.add(data);
	}
	
	return list;
}

		
}
