package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @GetMapping("/")
    public String indexPage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/user/profile");
            return null;
        }
        model.addAttribute("user", new UserDTO());
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "service/access-denied";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        String email = userDTO.getEmail();
        UserDTO userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null) {
            model.addAttribute("registrationError", true);
            model.addAttribute("user", userDTO);
            return "index";
        }
        userService.createUser(userDTO);
        return "service/registration-confirmation";
    }

}
