package com.bk.shop4.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bk.shop4.demo.Domain.Receipt;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> {
	
	  Optional<Receipt> findByUuidAndDeletedStatus(String uuid, boolean ds);
	    
	    List<Receipt> findByOrderUuidAndDeletedStatus(String uuid, boolean b);
	    
	    List<Receipt> findAllByDeletedStatus(boolean ds);

}
