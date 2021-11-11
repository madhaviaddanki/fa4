package com.infosys.infytel.order.dto;

import java.util.Date;

import com.infosys.infytel.order.entity.Orders;

public class OrdersDTO {

	private String orderId;
	private String buyerId;
	private Integer amount;
	private Date date;
	private String address;
	private String status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public OrdersDTO() {
		super();
	}

	// Converts Entity into DTO
	public static OrdersDTO valueOf(Orders plan) {
		OrdersDTO orderDTO= new OrdersDTO();
		orderDTO.setOrderId(plan.getOrderId());
		orderDTO.setBuyerId(plan.getBuyerId());
		orderDTO.setAmount(plan.getAmount());
		orderDTO.setDate(plan.getDate());
		orderDTO.setAddress(plan.getAddress());
		orderDTO.setStatus(plan.getStatus());
		return orderDTO;
	}
	
//	Converts DTO into Entity
	public Orders createEntity() {
		Orders Orders = new Orders();
		Orders.setOrderId(this.getOrderId());
		Orders.setBuyerId(this.getBuyerId());
		Orders.setAddress(this.getAddress());
		Orders.setAmount(this.getAmount());
		Orders.setDate(this.getDate());
		Orders.setStatus(this.getStatus());	
		return Orders;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [buyerId=" + buyerId + ", amount=" + amount + ", date=" + date
				+ ", address=" + address + ", status=" + status + "]";
	}

		

		


}
