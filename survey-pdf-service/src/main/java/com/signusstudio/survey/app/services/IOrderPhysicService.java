package com.signusstudio.survey.app.services;

import java.util.List;

import com.signusstudio.survey.app.models.entity.OrderPhysic;

public interface IOrderPhysicService {
    public OrderPhysic findOrderById(Long id);
    public List<OrderPhysic> list();
}
