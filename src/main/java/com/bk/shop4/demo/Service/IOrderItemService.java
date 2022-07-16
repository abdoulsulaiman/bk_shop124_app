package com.bk.shop4.demo.Service;

import java.util.List;


import com.bk.shop4.demo.Domain.OrderItem;

public interface IOrderItemService {
	OrderItem create(OrderItem item);

	void delete(String uuid);

	OrderItem findByUuid(String uuid);

	List<OrderItem> findByClientOrder(String uuid);
}
