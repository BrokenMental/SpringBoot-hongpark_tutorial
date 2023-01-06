package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceTomMeetYou(Model model) {
        model.addAttribute("username", "jacob");
        return "greetings"; //templates/greetings.mustache
    }
}
