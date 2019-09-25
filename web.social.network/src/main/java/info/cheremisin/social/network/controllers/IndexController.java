package info.cheremisin.social.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static info.cheremisin.social.network.common.Utils.getUserFromRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(HttpServletRequest request) {
        if(getUserFromRequest(request) != null) {
            return "profile";
        }
        return "index";
    }

}
