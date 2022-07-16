package com.bk.shop4.demo.Service;

import java.util.List;
import java.util.Map;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Client;

import com.bk.shop4.demo.InnerDomain.InnerClientData;



public interface IClientService {
	
	Client create(Client client);

	void delete(String uuid);

	Client findByUuid(String uuid);

	List<Client> findAll();
	
	Client CreateInitial(InnerClientData client);
	
	List<Cargo>GetClosestCargo(String uuid);

}
