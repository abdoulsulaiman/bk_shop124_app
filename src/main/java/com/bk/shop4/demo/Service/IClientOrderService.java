package com.bk.shop4.demo.Service;

import java.util.List;
import java.util.Map;

import com.bk.shop4.demo.Domain.ClientOrder;
import com.bk.shop4.demo.InnerDomain.InnerClientTopFiveOrder;
import com.bk.shop4.demo.InnerDomain.InnerCompleteOrder;
import com.bk.shop4.demo.InnerDomain.InnerOrderData;

public interface IClientOrderService {
	ClientOrder create(ClientOrder c);

	void delete(String uuid);

	ClientOrder findByUuid(String uuid);
	List<ClientOrder> findByClient(String uuid);
	List<ClientOrder> findByCargo(String uuid);
	List<ClientOrder> findAll();
	
	String createInitialOrder(InnerOrderData data);
	ClientOrder completeOrder(InnerCompleteOrder data);
	List<ClientOrder>getMostPaidOrder();
	List<InnerClientTopFiveOrder>getClientTopFiveOrder();
}
