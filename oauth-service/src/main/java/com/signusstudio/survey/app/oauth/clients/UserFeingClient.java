package com.signusstudio.survey.app.oauth.clients;

import com.signusstudio.survey.commons.user.models.entity.User;
import com.signusstudio.survey.commons.user.models.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;

@FeignClient(name = "user-service")
public interface UserFeingClient {

    @GetMapping("/users/search/find-username")
    public User findByUserName(@RequestParam String username);

    @PutMapping("/users/{id}")
    public User update(@RequestBody User user, @PathVariable Long id);

    @GetMapping("/users/{id}/roles")
    public CollectionModel<Role> findRolesByUserId(@PathVariable Long id);
}
