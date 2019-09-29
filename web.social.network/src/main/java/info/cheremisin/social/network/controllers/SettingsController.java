package info.cheremisin.social.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SettingsController {

    @GetMapping("/settings")
    public String getSettingsPage() {
        return "settings";
    }
}
