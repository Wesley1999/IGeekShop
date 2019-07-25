package com.igeek.igeekshop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String userId;

    private String username;

    private String password;

    private String name;

    private String email;

    private Integer gender;

    private Date birthday;

    private String telephone;

    private Boolean activeStatus;

}