package com.signusstudio.survey.app.models.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class History implements Serializable {

    private Long id;
    private String historyNumber;
    private Long patientId;
    private Long professionalId;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateEntered;

    private String consultType;
    private String finalidad;
    private String motiveConsult;
    private String causeExterna;
    private String enfermedadActual;
    private String revisionSistemas;
    private String presionArterial;
    private String presionArterialMedia;
    private String temperature;
    private String frecuenciaCardiaca;
    private String frecuenciaRespiratoria;
    private String saturacionOxigeno;
    private String peso;
    private String talla;
    private String imc;
    private String zonasAnatomicas;

    private String antecedentesAlergicos;
    private String antecedentesEmocionales;
    private String antecedentesGenerales;
    private String antecedentesGinecologicos;
    private String antecedentesPatologicos;
    private String antecedentesPediatricos;
    private String antecedentesPlanificacion;
    private String antecedentesQuirurgicos;
    private String antecedentesSexuales;
    private String antecedentesToxicos;
    private String antecedentesTraumaticos;
    private String antecedentesVacunacion;
    private String antecedentesFamiliares;

    private Date fechaEvolucion;
    private String texto;
    private String firmaElectronica;
    private String cie10;

    private String recomendacionesEducativas;
    private String internacion;
    private String otros;

}
