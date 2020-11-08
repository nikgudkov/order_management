package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping("/secure_page")
    public String securePage() {
        return "secure_page";
    }

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
}
