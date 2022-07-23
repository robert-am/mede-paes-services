package com.signusstudio.survey.app.services;

import java.util.List;

public interface IOrderPhysicService {
    public OrderPhysic findOrderById(Long id);
    public List<OrderPhysic> list();
}
