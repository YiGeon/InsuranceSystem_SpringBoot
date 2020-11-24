package com.InsuranceSystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MyController {

    @GetMapping(path = "/main")
    public String openMain(Model model) {
        System.err.println("get main");
        return "/main";
    }

    @GetMapping(path = "/develop")
    public String openDevelop(Model model) {
        System.err.println("get develop");
        return "/dev/type";
    }

    @GetMapping(path = "/develop/life")
    public String devLife(Model model) {
        System.err.println("get dev/life");
        return "/dev/life";
    }

    @GetMapping(path = "/develop/fire")
    public String devFire(Model model) {
        System.err.println("get dev/fire");
        return "/dev/fire";
    }

    @GetMapping(path = "/develop/loss")
    public String devLoss(Model model) {
        System.err.println("get dev/loss");
        return "/dev/loss";
    }

    @GetMapping(path = "/obtain")
    public String devObtain(Model model) {
        System.err.println("get obtain");
        return "/dev/obtain";
    }

}