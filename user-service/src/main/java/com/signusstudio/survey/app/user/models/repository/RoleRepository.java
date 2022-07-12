package com.signusstudio.survey.app.user.models.repository;

import com.signusstudio.survey.commons.user.models.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "roles")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    @RestResource(path = "find-name")
    public Role findByName(String name);
}
