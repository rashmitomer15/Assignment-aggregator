package com.assessment.microservices.aggregator.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderDetails {

	private User userDetails;
	private List<Order> orders;


	public OrderDetails() {
		super();
	}

	public OrderDetails(List<Order> orders, User userDetails) {
		super();
		this.orders = orders;
		this.userDetails = userDetails;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "OrderDetails [orders=" + orders + ", userDetails=" + userDetails + "]";
	}

}
