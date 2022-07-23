package com.signusstudio.survey.app.controller;

import com.signusstudio.survey.app.models.entity.History;
import com.signusstudio.survey.app.services.IHistoryService;
import com.signusstudio.survey.app.services.IOrderPhysicService;
import com.signusstudio.survey.app.services.IPacienteService;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RestController
public class PhysicController {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IOrderPhysicService orderPhysicService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPDF(@PathVariable(value = "id") String id) {
        final Context ctx = new Context();
        //History history = historyService.findHistoryById(order.getHistory());
        ctx.setVariable("data", "history");
        String html = templateEngine.process("physic-tamplate", ctx);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, target);
        byte[] bytes = target.toByteArray();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
