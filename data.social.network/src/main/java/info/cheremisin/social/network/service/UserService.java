package info.cheremisin.social.network.service;

import info.cheremisin.social.network.dto.UserDTO;

public interface UserService {

    UserDTO getUserByEmail(String email);

    void saveUser(UserDTO userDTO);

}
