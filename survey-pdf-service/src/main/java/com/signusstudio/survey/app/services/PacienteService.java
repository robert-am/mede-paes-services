package com.signusstudio.survey.app.services;

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
