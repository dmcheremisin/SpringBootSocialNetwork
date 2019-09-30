package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "index";
        }

        String email = userDTO.getEmail();
        UserDTO userByEmail = userService.getUserByEmail(email);
        if(userByEmail != null) {
            model.addAttribute("registrationError", true);
            model.addAttribute("user", new UserDTO());
            return "index";
        }
        userService.createUser(userDTO);
        return "registration-confirmation";
    }

}
