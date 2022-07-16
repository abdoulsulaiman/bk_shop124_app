package com.bk.shop4.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.InnerDomain.InnerClientData;
import com.bk.shop4.demo.Repository.CargoRepo;
import com.bk.shop4.demo.Repository.ClientRepo;
import com.bk.shop4.demo.Service.IClientService;
import com.bk.shop4.demo.Utility.ObjectNotFoundException;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	ClientRepo clientrepo;
	
	@Autowired
	CargoRepo cargoRepo;

	@Override
	public Client create(Client client) {
		
		return clientrepo.save(client);
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Client> cl = clientrepo.findByUuidAndDeletedStatus(uuid, false);
			if (cl.isPresent()) {
				Client client = cl.get();
				client.setDeletedStatus(true);
				this.create(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Client findByUuid(String uuid) {
		Optional<Client> cl = clientrepo.findByUuidAndDeletedStatus(uuid, false);
        if (cl.isPresent()) {
            return cl.get();
        } else {
            throw new ObjectNotFoundException("Client not found!");
        }
	}

	@Override
	public List<Client> findAll() {
	
		return clientrepo.findByDeletedStatus(false);
	}

	@Override
	public Client CreateInitial(InnerClientData client) {
		Client c=new Client();
		c.setNames(client.getNames());
		c.setEmail(client.getEmail());
		c.setPhone(client.getPhone());
		c.setAddress(client.getAddress());
		c.setLatitude(client.getLatitude());
		c.setLongitude(client.getLongitude());
		clientrepo.save(c);
		return c;
	}
	private double Calculatedistance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		    dist = dist * 1.609344;

		  return (dist);

		}




		private double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);

		}


		private double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
		}

	@Override
	public List<Cargo> GetClosestCargo(String uuid) {
		Optional<Client> client=clientrepo.findByUuidAndDeletedStatus(uuid, false);
		List<Cargo>cargo_list=new ArrayList<Cargo>();
		if(client.isPresent()) {
			Client cl =client.get();
		
		for (Cargo c:cargoRepo.findByDeletedStatus(false)) {
			if(Calculatedistance(Double.parseDouble(cl.getLatitude()), Double.parseDouble(cl.getLongitude()), Double.parseDouble(c.getLatitude()), Double.parseDouble(c.getLongitude()))<3) {
				cargo_list.add(c);
			}
		}
		}
		return cargo_list;
	}
	
	

}
