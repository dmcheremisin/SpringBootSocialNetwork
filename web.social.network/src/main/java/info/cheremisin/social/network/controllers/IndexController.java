package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("user") != null) {
            return "profile";
        }
        model.addAttribute("user", new UserDTO());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "registration-confirmation";
    }

}
