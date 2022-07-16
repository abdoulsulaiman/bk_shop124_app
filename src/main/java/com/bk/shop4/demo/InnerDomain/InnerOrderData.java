package com.bk.shop4.demo.InnerDomain;

import java.util.List;

public class InnerOrderData {
	
	private String clientUuid;
	private List<DrinkData> list;
	
	public String getClientUuid() {
		return clientUuid;
	}
	public void setClientUuid(String clientUuid) {
		this.clientUuid = clientUuid;
	}
	public List<DrinkData> getList() {
		return list;
	}
	public void setList(List<DrinkData> list) {
		this.list = list;
	}
	
	
}

