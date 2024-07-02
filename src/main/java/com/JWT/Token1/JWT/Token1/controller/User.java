package com.JWT.Token1.JWT.Token1.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/User")
public class User {

    //http://localhost:8080/api/v1/User/AddUser

    @PostMapping("/AddUser")
    public String AddUser(){
        return "Done User";
    }
}
