package com.signusstudio.survey.app.models.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Boolean enable;
    private String firstName;
    private String lastName;
    private String type;
    private String email;
    private Integer retries;
    private String signature;
   
}