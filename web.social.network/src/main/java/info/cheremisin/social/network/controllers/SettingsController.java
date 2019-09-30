package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class SettingsController {

    private UserService userService;

    @GetMapping("/settings")
    public String getSettingsPage(HttpServletRequest request, Model model) {
        UserDTO user = (UserDTO)request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings")
    public void updateSettings(HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("user") UserDTO user) throws IOException {
        userService.updateUser(user);

        request.getSession().setAttribute("user", userService.getUserByEmail(user.getEmail()));
        response.sendRedirect(request.getContextPath() + "/user/settings");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
