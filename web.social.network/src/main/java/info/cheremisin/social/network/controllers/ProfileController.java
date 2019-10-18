package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.FriendsService;
import info.cheremisin.social.network.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private UserService userService;
    private FriendsService friendsService;

    public ProfileController(UserService userService, FriendsService friendsService) {
        this.userService = userService;
        this.friendsService = friendsService;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpServletRequest request) {
        UserDTO user = getUserFromSession(request);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String getUserPage(@PathVariable Long id, Model model, HttpServletRequest request) {
        UserDTO sessionUser = getUserFromSession(request);
        UserDTO user = userService.getUserById(id);
        Boolean friendship = friendsService.checkFriendship(sessionUser, user);
        model.addAttribute("user", user);
        model.addAttribute("usersHaveFriendship", friendship);
        return "profile";
    }
}
