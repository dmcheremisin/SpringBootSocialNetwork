package info.cheremisin.social.network.service;

import info.cheremisin.social.network.dto.UserDTO;

public interface UserService {

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    Byte[] getUserImage(Long id);

    void createUser(UserDTO userDTO);

    void updatePassword(String password, Long id);

    void updateUser(UserDTO userDTO);

    void updateUserImage(UserDTO userDTO, byte[] image);

}
