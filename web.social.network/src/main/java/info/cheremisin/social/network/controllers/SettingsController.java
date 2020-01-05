package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.PasswordChangeDTO;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.exceptions.SocialNetworkException;
import info.cheremisin.social.network.service.ImageService;
import info.cheremisin.social.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class SettingsController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageService imageService;

    @Value("#{'${allowed.file.types}'.split(',')}")
    private Set<String> allowedExtensions;

    @GetMapping("/settings")
    public String getSettingsPage(Model model) {
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        return "settings";
    }

    @PostMapping("/settings")
    public String updateSettings(HttpServletRequest request, @ModelAttribute("user") UserDTO user)  {
        userService.updateUser(user);

        request.getSession().setAttribute("user", userService.getUserByEmail(user.getEmail()));
        return "redirect:/user/settings";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@Valid @ModelAttribute("passwordChangeDTO") PasswordChangeDTO passwordChangeDTO,
                                 BindingResult bindingResult, HttpServletRequest request, Model model) {
        if(bindingResult.hasErrors()) {
            return "settings";
        }
        UserDTO user = getUserFromSession(request);
        String password = user.getPassword();
        boolean passwordsMatch = bCryptPasswordEncoder.matches(passwordChangeDTO.getOldPassword(), password);
        if(!passwordsMatch) {
            model.addAttribute("passwordError", "Passwords doesn't match");
        } else {
            userService.updatePassword(passwordChangeDTO.getPassword(), user.getId());
            request.getSession().setAttribute("user", userService.getUserByEmail(user.getEmail()));
        }
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        return "settings";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(MultipartHttpServletRequest request) throws IOException {
        MultipartFile multipartFile = request.getFile("imagefile");
        String contentType = multipartFile.getContentType();
        if(!allowedExtensions.contains(contentType)) {
            throw new SocialNetworkException("File extension is not supported");
        }

        UserDTO user = getUserFromSession(request);

        String newFileName = imageService.updateProfileImage(user, multipartFile);
        userService.updateUserImage(user, newFileName);
        return "redirect:/user/settings";
    }

}
