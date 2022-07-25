package com.signusstudio.survey.app.services;


import com.signusstudio.survey.app.client.HistoryFeingClient;
import com.signusstudio.survey.app.models.entity.History;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HistoryService implements IHistoryService {
    private Logger log  = LoggerFactory.getLogger(HistoryService.class);
    @Autowired
    HistoryFeingClient client;
    @Override
    public History findHistoryById(Long id) {
            return client.findById(id);
    }

}
