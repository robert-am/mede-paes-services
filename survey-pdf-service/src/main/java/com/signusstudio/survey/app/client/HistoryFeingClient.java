package com.signusstudio.survey.app.client;

import com.signusstudio.survey.app.models.entity.History;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "history-service")
public interface HistoryFeingClient {
    
    @GetMapping("/event/{id}")
    public History findById(@PathVariable Long id);

}
