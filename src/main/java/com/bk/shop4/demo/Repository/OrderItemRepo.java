package com.bk.shop4.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.bk.shop4.demo.Domain.ClientOrder;
import com.bk.shop4.demo.Domain.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
	
	List<OrderItem> findByClientOrderAndDeletedStatus(ClientOrder order, boolean b);

    List<OrderItem> findByClientOrderUuidAndDeletedStatus(String uuid, boolean b);

    Optional<OrderItem> findByUuidAndDeletedStatus(String uuid, boolean ds);
    
    List<OrderItem> findByDrinkUuidAndDeletedStatus(String uuid, boolean b);

    List<OrderItem> findAllByDeletedStatus(boolean ds);
    
}
