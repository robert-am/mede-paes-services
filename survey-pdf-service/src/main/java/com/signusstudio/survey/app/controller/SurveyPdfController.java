package com.signusstudio.survey.app.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;

import com.signusstudio.survey.app.services.IHistoryService;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static com.itextpdf.html2pdf.css.CssConstants.LANDSCAPE;

@RestController
public class SurveyPdfController {
    @Autowired
    private SpringTemplateEngine templateEngine;

    /*@Autowired
    private IHistoryService historyService;

     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPDF(@PathVariable(value = "id") String id) {
        final Context ctx = new Context();
        //History history = historyService.findHistoryById(order.getHistory());
        ctx.setVariable("data", "history");
        String html = templateEngine.process("1653-template", ctx);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties properties = new ConverterProperties();


        MediaDeviceDescription med = new MediaDeviceDescription(MediaType.PRINT);
        med.setOrientation(LANDSCAPE);
        properties.setMediaDeviceDescription(med);

        HtmlConverter.convertToPdf(html, target,  properties);
        byte[] bytes = target.toByteArray();
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
