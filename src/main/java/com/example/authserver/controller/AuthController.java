package com.example.authserver.controller;

import com.example.authserver.entity.Otp;
import com.example.authserver.entity.User;
import com.example.authserver.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    //curl -XPOST -H 'Content-Type: application/json' -d '{"username":"john","password":"12345"}' localhost:8080/user/add
    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PostMapping("/user/auth")
    public void auth(@RequestBody User user){
        userService.auth(user);
    }
    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response){
        if(userService.check(otp)){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
