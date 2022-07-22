package com.signusstudio.survey.app.oauth.services;

import brave.Tracer;
import com.signusstudio.survey.app.oauth.clients.UserFeingClient;
import com.signusstudio.survey.commons.user.models.entity.User;
import com.signusstudio.survey.commons.user.models.entity.Role;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toCollection;

@Service
public class UserService implements IUserService, UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserFeingClient client;

    @Autowired
    private Tracer tracer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = this.findByUsername(username);
            List<Role> roles = this.findRolesByUserId(user.getId());
            List<GrantedAuthority> authorities = roles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek(autority -> log.info("Role: " + autority.getAuthority()))
                    .collect(Collectors.toList());
            log.info("Usuario autenticado: " + username);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getStatus(),
                    true,
                    true,
                    true,
                    authorities);
        } catch (FeignException e) {
            String  error = "Error de login, No existe el usuario '" + username + "' en el sistema";
            log.error(error);
            tracer.currentSpan().tag("error.message", error + ": " + e.getMessage());
            throw new UsernameNotFoundException("Error de login, No existe el usuario '" + username + "' en el sistema");
        }
    }

    @Override
    public User findByUsername(String username) {
        return client.findByUserName(username);
    }

    @Override
    public User update(User user, Long id) {
        return client.update(user, id);
    }

    @Override 
    public List<Role> findRolesByUserId(Long id){
        return client.findRolesByUserId(id).getContent().stream().collect(toCollection(ArrayList::new));
    }
}
