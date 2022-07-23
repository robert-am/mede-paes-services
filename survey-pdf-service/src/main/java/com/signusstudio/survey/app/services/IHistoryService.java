package com.signusstudio.survey.app.services;

import com.signusstudio.survey.app.models.entity.History;

public interface IHistoryService {

    public History findHistoryById(Long id);
    
}
