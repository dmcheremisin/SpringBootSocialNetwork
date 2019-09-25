package info.cheremisin.social.network.common;

import info.cheremisin.social.network.entities.User;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static User getUserFromRequest(HttpServletRequest request) {
        return request.getSession() != null ? (User) request.getSession().getAttribute("user") : null;
    }
}
