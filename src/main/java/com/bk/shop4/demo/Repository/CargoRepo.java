package com.bk.shop4.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bk.shop4.demo.Domain.Cargo;


@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {
	
	Optional<Cargo> findByUuidAndDeletedStatus(String uuid, Boolean deletedStatus);

    Optional<Cargo> findByNamesAndDeletedStatus(String name, Boolean deletedStatus);

    List<Cargo> findByDeletedStatus(Boolean deletedStatus);
	
}
