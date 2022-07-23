package com.signusstudio.survey.app.services;

import com.signusstudio.survey.app.models.entity.Paciente;

public interface IPacienteService {
    public Paciente findPacienteById(Long id);    
}
