package info.cheremisin.social.network.controllers;

import info.cheremisin.social.network.dto.PasswordChangeDTO;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static info.cheremisin.social.network.utils.ServerUtils.getUserFromSession;

@Controller
@RequestMapping("/user")
public class SettingsController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/settings")
    public String getSettingsPage(HttpServletRequest request, Model model) {
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        return "settings";
    }

    @PostMapping("/settings")
    public void updateSettings(HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("user") UserDTO user) throws IOException {
        userService.updateUser(user);

        request.getSession().setAttribute("user", userService.getUserByEmail(user.getEmail()));
        response.sendRedirect(request.getContextPath() + "/user/settings");
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
    public void uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartFile multipartFile = request.getFile("imagefile");
        byte[] bytes = multipartFile.getBytes();
        UserDTO user = getUserFromSession(request);
        userService.updateUserImage(user, bytes);
        response.sendRedirect(request.getContextPath() + "/user/settings");
    }

    @GetMapping("/{id}/image")
    public void getImageFromDb(@PathVariable Long id, HttpServletResponse response) throws IOException {
        UserDTO user = userService.getUserById(id);
        if (user.getImage() != null) {
            byte[] byteArray = new byte[user.getImage().length];
            int i = 0;

            for (Byte wrappedByte : user.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}
