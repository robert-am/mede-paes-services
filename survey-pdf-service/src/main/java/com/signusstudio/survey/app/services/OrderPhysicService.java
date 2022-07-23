package com.signusstudio.survey.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPhysicService implements IOrderPhysicService {
    private Logger log = LoggerFactory.getLogger(OrderPhysicService.class);

    @Autowired
    OrderPhysicFeingClient client;

    @Override
    public OrderPhysic findOrderById(Long id){
        return client.findById(id);
    }

	@Override
	public List<OrderPhysic> list() {
		return client.find();
	}
}
