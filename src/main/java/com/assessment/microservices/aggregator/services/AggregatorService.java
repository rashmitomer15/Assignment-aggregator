package com.assessment.microservices.aggregator.services;

import com.assessment.microservices.aggregator.model.OrderDetails;

public interface AggregatorService {

	OrderDetails getOrderDetails(Integer userId);

}
