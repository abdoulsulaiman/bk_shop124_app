package com.bk.shop4.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bk.shop4.demo.Domain.Client;


@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
	
	Optional<Client> findByUuidAndDeletedStatus(String uuid, Boolean deletedStatus);

    Optional<Client> findByNamesAndDeletedStatus(String name, Boolean deletedStatus);

    List<Client> findByDeletedStatus(Boolean deletedStatus);

}
