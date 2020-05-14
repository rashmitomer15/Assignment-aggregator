package com.assessment.microservices.aggregator.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assessment.microservices.aggregator.model.OrderDetails;
import com.assessment.microservices.aggregator.services.AggregatorService;

@RestController
@RequestMapping(value = "/orderdetails")
public class AggregatorController {

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;

	@Value("${server.port}")
	private int port;

	@Resource
	AggregatorService aggregatorService;

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable(name = "userId") int userId) {
		OrderDetails orderDetails = aggregatorService.getOrderDetails(userId);
		return new ResponseEntity<OrderDetails>(orderDetails,HttpStatus.OK);
	}

}
