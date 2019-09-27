package info.cheremisin.social.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile";
    }
}
