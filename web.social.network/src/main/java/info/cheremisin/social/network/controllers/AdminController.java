package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/makeAdmin")
    public String makeAdmin(@RequestParam Long userId, HttpServletRequest request) {
        userService.makeUserAdmin(userId);
        return "redirect:/user/profile/" + userId;
    }

    @PostMapping("/block")
    public String blockUser(@RequestParam Long userId, HttpServletRequest request) {
        userService.blockUser(userId);
        return "redirect:/user/profile/" + userId;
    }

}
