package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.FriendsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user/friends")
public class FriendsController {

    private FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

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
    public void deleteFriendship(@PathVariable Long friendId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = getUserFromSession(request);
        friendsService.deleteFriendship(user, friendId);
        response.sendRedirect(request.getContextPath() + "/user/friends");
    }

    @GetMapping("/{friendId}/accept")
    public void acceptFriendship(@PathVariable Long friendId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = getUserFromSession(request);
        friendsService.acceptFriendship(user, friendId);
        response.sendRedirect(request.getContextPath() + "/user/friends");
    }

    @GetMapping("/{friendId}/addToFriends")
    public void addToFriends(@PathVariable Long friendId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = getUserFromSession(request);
        friendsService.addToFriends(user, friendId);
        response.sendRedirect(request.getContextPath() + "/user/friends");
    }

}
