package com.signusstudio.survey.app.oauth.services;

import com.signusstudio.survey.commons.user.models.entity.User;
import com.signusstudio.survey.commons.user.models.entity.Role;

import java.util.List;

public interface IUserService {

    public User findByUsername(String username);
    public User update( User user, Long id);
    public List<Role> findRolesByUserId(Long id);
}
