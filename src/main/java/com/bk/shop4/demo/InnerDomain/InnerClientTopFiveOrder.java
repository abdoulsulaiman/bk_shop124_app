package com.bk.shop4.demo.InnerDomain;

import java.util.List;

import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.Domain.ClientOrder;

public class InnerClientTopFiveOrder {
	private Client client;
	private List<ClientOrder>list;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ClientOrder> getList() {
		return list;
	}
	public void setList(List<ClientOrder> list) {
		this.list = list;
	}
	
	
}
