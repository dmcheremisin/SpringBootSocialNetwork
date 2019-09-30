package info.cheremisin.social.network.utils;

import info.cheremisin.social.network.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public class ServerUtils {

    public static UserDTO getUserFromSession(HttpServletRequest request){
        return (UserDTO) request.getSession().getAttribute("user");
    }
}
