package com.br.garagem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public Boolean authentication() {
        this.service.getUserByNickName();
        return true;
    }

}
