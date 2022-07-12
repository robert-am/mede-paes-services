package com.signusstudio.survey.app.oauth.security.event;

import brave.Tracer;
import com.signusstudio.survey.app.oauth.services.IUserService;
import com.signusstudio.survey.commons.user.models.entity.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    IUserService userService;

    @Autowired
    Tracer tracer;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String message = "Success Login: " + user.getUsername();
        System.out.println(message);
        log.info(message);
        try {
            User userPO = userService.findByUsername(authentication.getName());
            if (userPO.getRetries() != null && userPO.getRetries() > 0) {
                userPO.setRetries(0);
                userService.update(userPO, userPO.getId());
            }
        } catch (Exception e) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String message = "Error en el login: " + e.getMessage();
        log.error(message);
        System.out.println(message);
        try {
            StringBuilder errors = new StringBuilder();
            errors.append(message);
            User user = userService.findByUsername(authentication.getName());
            if (user.getRetries() == null) {
                user.setRetries(0);
            }
            log.info("Intentos actuales: " + user.getRetries());
            user.setRetries(user.getRetries() + 1);

            log.info("Intentos despues de: " + user.getRetries());
            errors.append("Intentos despues de: " + user.getRetries());
            if (user.getRetries() >= 3) {
                String errorMaxRetries = String.format("El usuario %s ha sido deshabilitado por la cantidad de intentos", authentication.getName());
                log.error(String.format("El usuario %s ha sido deshabilitado por la cantidad de intentos", authentication.getName()));
                errors.append(" - " + errorMaxRetries);
                user.setEnable(false);
            }
            userService.update(user, user.getId());
            tracer.currentSpan().tag("error.message", errors.toString());
        } catch (FeignException fe) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }

    }
}
