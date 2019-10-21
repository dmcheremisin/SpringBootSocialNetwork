package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.MessageDTO;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.MessagesService;
import info.cheremisin.social.network.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user")
public class MessagesController {

    private MessagesService messagesService;
    private UserService userService;

    public MessagesController(MessagesService messagesService, UserService userService) {
        this.messagesService = messagesService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    public String getMessages(HttpServletRequest request, Model model) {
        UserDTO user = getUserFromSession(request);
        Collection<MessageDTO> recentMessages = messagesService.findAllRecentMessages(user.getId());
        model.addAttribute("recentMessages", recentMessages);
        return "messages";
    }

    @GetMapping("/conversation/{companionId}")
    public String getConversation(@PathVariable Long companionId, HttpServletRequest request, Model model) {
        UserDTO user = getUserFromSession(request);
        UserDTO companion = userService.getUserById(companionId);
        List<MessageDTO> messages = messagesService.findConversation(user.getId(), companionId);
        model.addAttribute("messages", messages);
        model.addAttribute("companion", companion);
        return "conversation";
    }

}
