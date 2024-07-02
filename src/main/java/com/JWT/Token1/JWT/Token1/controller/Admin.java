package com.JWT.Token1.JWT.Token1.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Admin")
public class Admin {

    //http://localhost:8080/api/v1/Admin/AddAdmin

    @PostMapping("/AddAdmin")
    public String AddAdmin(){
        return "Done Admin";
    }
}
