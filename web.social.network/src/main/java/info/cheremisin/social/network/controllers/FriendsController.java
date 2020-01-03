package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final FriendsService friendsService;

    @GetMapping
    public String getAllFriends(@RequestParam(value = "search", required = false) String search,
            Model model, HttpServletRequest request) {
        UserDTO user = getUserFromSession(request);
        Map<String, Set<UserDTO>> friends = friendsService.getFriends(user.getId(), search);
        model.addAttribute("usersNotAcceptedRequests", friends.get("usersNotAcceptedRequests"));
        model.addAttribute("notAcceptedRequestsToUser", friends.get("notAcceptedRequestsToUser"));
        model.addAttribute("friendsOfUser", friends.get("friendsOfUser"));
        return "friends";
    }

    @GetMapping("/{friendId}/decline")
    public String deleteFriendship(@PathVariable Long friendId, HttpServletRequest request) {
        UserDTO user = getUserFromSession(request);
        friendsService.deleteFriendship(user, friendId);
        return "redirect:/user/friends";
    }

    @GetMapping("/{friendId}/accept")
    public String acceptFriendship(@PathVariable Long friendId, HttpServletRequest request) {
        UserDTO user = getUserFromSession(request);
        friendsService.acceptFriendship(user, friendId);
        return "redirect:/user/friends";
    }

    @GetMapping("/{friendId}/addToFriends")
    public String addToFriends(@PathVariable Long friendId, HttpServletRequest request) {
        UserDTO user = getUserFromSession(request);
        friendsService.addToFriends(user, friendId);
        return "redirect:/user/friends";
    }

}
