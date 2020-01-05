package info.cheremisin.social.network.utils;

import info.cheremisin.social.network.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;

import static info.cheremisin.social.network.constants.Constants.PROFILE_IMAGES;

public class ServerUtils {

    public static UserDTO getUserFromSession(HttpServletRequest request){
        return (UserDTO) request.getSession().getAttribute("user");
    }

    public static Path getProfileImagesPath() {
        return Paths.get(".").resolve(PROFILE_IMAGES);
    }
}
