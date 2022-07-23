package com.signusstudio.survey.app.services;

import com.signusstudio.survey.app.client.PacienteFeingClient;
import com.signusstudio.survey.app.models.entity.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    PacienteFeingClient client;
    @Override
    public Paciente findPacienteById(Long id) {
        return client.findById(id);
    }
    
}
