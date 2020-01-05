package info.cheremisin.social.network.config;

import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		UserDTO userDTO = userService.getUserByEmail(authentication.getName());
		request.getSession().setAttribute("user", userDTO);

		response.sendRedirect(request.getContextPath() + "/user/profile");
	}

}
