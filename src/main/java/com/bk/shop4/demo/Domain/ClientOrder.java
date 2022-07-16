package com.bk.shop4.demo.Domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class) 
public class ClientOrder extends Metadata implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	private String orderCode;
	@Column(nullable=true)
	private Timestamp deliveryStartTime;
	@Column(nullable=true)
	private Timestamp deliveryEndTime;
	private String cargorefrenceId;
	
	@ManyToOne
	public Client client;
	
	@ManyToOne
	public Cargo cargo;
	
	private String comment;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Timestamp getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Timestamp deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Timestamp getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Timestamp deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	
	public String getCargorefrenceId() {
		return cargorefrenceId;
	}

	public void setCargorefrenceId(String cargorefrenceId) {
		this.cargorefrenceId = cargorefrenceId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
