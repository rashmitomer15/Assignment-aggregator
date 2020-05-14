package com.assessment.microservices.aggregator.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.microservices.aggregator.model.OrderDetails;
import com.assessment.microservices.aggregator.model.User;
import com.assessment.microservices.aggregator.services.AggregatorService;

@Service
public class DefaultAggregatorService implements AggregatorService {

	@Autowired
	//@LoadBalanced
	private RestTemplate restTemplate;
	
	@Value("${server.port}")
	private int port;
	

	@Override
	public OrderDetails getOrderDetails(Integer userId) {

		OrderDetails orderDet = new OrderDetails();
		
		String userServiceBaseUrl=System.getenv().getOrDefault("USERS_SERVICE_URL", "http://localhost:8040/user");

		String url = userServiceBaseUrl +"/"+ userId;
		ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, null, User.class);
		User user = response.getBody();

		orderDet.setUserDetails(user);
		
		String orderServiceBaseUrl=System.getenv().getOrDefault("ORDERS_SERVICE_URL", "http://localhost:8050/orders");

		String url2 = orderServiceBaseUrl + "/"+userId;
		ResponseEntity<List> response1 = restTemplate.exchange(url2, HttpMethod.GET, null, List.class);
		List orderList = response1.getBody();
		
		orderDet.setOrders(orderList);

		return orderDet;
	}

}
