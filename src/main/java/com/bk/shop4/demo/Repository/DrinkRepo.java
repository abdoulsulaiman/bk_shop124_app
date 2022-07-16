package com.bk.shop4.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bk.shop4.demo.Domain.Drink;


@Repository
public interface DrinkRepo extends JpaRepository<Drink, Long> {
	
	Optional<Drink> findByUuidAndDeletedStatus(String uuid, Boolean deletedStatus);

    Optional<Drink> findByAvailabilityAndDeletedStatus(String status, Boolean deletedStatus);

    List<Drink> findByDeletedStatus(Boolean deletedStatus);

}
