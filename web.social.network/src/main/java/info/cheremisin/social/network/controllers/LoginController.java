package info.cheremisin.social.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showMyLoginPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("user") != null) {
            return "profile";
        }
        return "index";
    }

}
