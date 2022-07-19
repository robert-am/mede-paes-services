package com.signusstudio.survey.app.survey.controllers;

import com.signusstudio.survey.app.survey.models.entity.MailMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {

    //private final Logger log = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("/")
    public String sendMail(@RequestBody MailMessage mailMessage) throws MessagingException, IOException {
        final Context ctx  = new Context();
        ctx.setVariable("name", mailMessage.getName());
        ctx.setVariable("tipo_consulta", mailMessage.getTipoConsulta());
        ctx.setVariable("fecha_cita", mailMessage.getFechaCita());
        ctx.setVariable("hora_cita", mailMessage.getHoraCita());
        ctx.setVariable("lugar_cita", mailMessage.getLugarCita());
        ctx.setVariable("consulta_id", mailMessage.getConsultaId());
        ctx.setVariable("profesional", mailMessage.getProfesional());
        ctx.setVariable("link", "http://portal.summarte.co/teleconsulta/" + mailMessage.getConsultaId() );
        String html = templateEngine.process("email-template", ctx);
        mailMessage.setContent(html);
        sendmail(mailMessage);
        return "Email sent sussesfully";
    }

    private void sendmail(MailMessage mailMessage) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificaciones@summasalud.com.co", "Summa.123+");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailMessage.getFrom(), false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailMessage.getTo().get(0)));
        msg.setSubject(mailMessage.getSubject());
        msg.setContent(mailMessage.getContent(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mailMessage.getContent(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        //MimeBodyPart attachPart = new MimeBodyPart();
        //attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);

        msg.setContent(multipart);
        Transport.send(msg);
    }
}
