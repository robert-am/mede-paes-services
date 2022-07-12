package com.signusstudio.survey.app.user.models.repository;


import com.signusstudio.survey.commons.user.models.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @RestResource(path = "find-username")
    public User findByUsername(String username);
}
