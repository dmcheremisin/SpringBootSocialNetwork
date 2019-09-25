package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static info.cheremisin.social.network.common.Utils.getUserFromRequest;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(HttpServletRequest request, Model model) {
        User userFromRequest = getUserFromRequest(request);
        model.addAttribute("user", userFromRequest);

        return "profile";
    }
}
