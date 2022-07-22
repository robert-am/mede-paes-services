package com.signusstudio.survey.commons.user.models.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60)
    private String password;
    private Boolean status;
    private String document;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String type;
    @Column(unique = true, length = 100)
    private String personalEmail;
    private String corporateEmail;
    private String approval;
    private String esanUser;
    private Integer retries;
    private String signature;
    private String agentCode;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;
}
