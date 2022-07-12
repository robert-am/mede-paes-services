package com.signusstudio.survey.app.mail.models.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MailMessage implements Serializable {
    String consultaId;
    String name;
    String tipoConsulta;
    String fechaCita;
    String horaCita;
    String lugarCita;
    String profesional;

    String from;
    List<String> to;
    String subject;
    String content;
}
