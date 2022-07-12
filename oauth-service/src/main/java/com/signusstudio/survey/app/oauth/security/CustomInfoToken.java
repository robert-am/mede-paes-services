package com.signusstudio.survey.app.oauth.security;

import com.signusstudio.survey.app.oauth.services.IUserService;
import com.signusstudio.survey.commons.user.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoToken implements TokenEnhancer {

    @Autowired
    IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();
        User user =  userService.findByUsername(oAuth2Authentication.getName());
        info.put("id", user.getId());
        info.put("name", user.getFirstName());
        info.put("lastname", user.getLastName());
        info.put("email", user.getEmail());
        info.put("role", user.getType());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
