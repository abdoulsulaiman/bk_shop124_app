package com.bk.shop4.demo.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.Domain.ClientOrder;



@Repository
public interface ClientOrderRepo extends JpaRepository<ClientOrder, Long> {
	
	 	List<ClientOrder> findByClientAndDeletedStatus(Client client, boolean b);

	    List<ClientOrder> findByClientUuidAndDeletedStatus(String uuid, boolean b);

	    Optional<ClientOrder> findByUuidAndDeletedStatus(String uuid, boolean ds);
	    
	    List<ClientOrder> findAllByDeletedStatus(boolean ds);
	    
	 	List<ClientOrder> findByCargoAndDeletedStatus(Client client, boolean b);
	 	
	 	 List<ClientOrder> findByCargoUuidAndDeletedStatus(String uuid, boolean b);
	 	 
	 	 @Query("Select i from ClientOrder i where i.cargo.uuid=:uuid AND i.doneAt>= :startDate AND i.doneAt<= :endDate ORDER BY i.doneAt ASC")
	     List<ClientOrder> findOrderByCargoAndTimeFrame(String uuid, Date startDate, Date endDate);
	 	 
	 	@Query("Select i from ClientOrder i where i.deletedStatus=0 AND i.cargo!=null  ORDER BY i.totalPrice DESC")
	    List<ClientOrder> findSortesAll();
	 	
	 	@Query("Select i from ClientOrder i where i.deletedStatus=0 ORDER BY i.doneAt DESC")
	    List<ClientOrder> findSorteByDate();
}
