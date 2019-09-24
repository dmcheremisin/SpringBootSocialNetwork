package info.cheremisin.social.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showMyLoginPage() {

        return "index";
    }

}
