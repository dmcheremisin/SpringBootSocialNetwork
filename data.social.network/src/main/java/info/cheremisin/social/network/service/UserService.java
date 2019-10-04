package info.cheremisin.social.network.service;

import info.cheremisin.social.network.dto.PageDTO;
import info.cheremisin.social.network.dto.UserDTO;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO getUserByEmail(String email);

    PageDTO<UserDTO> findAllPageable(Long id, Pageable pageable);

    PageDTO<UserDTO> findAllWithSearch(Long id, String search, Pageable pageable);

    Byte[] getUserImage(Long id);

    void createUser(UserDTO userDTO);

    void updatePassword(String password, Long id);

    void updateUser(UserDTO userDTO);

    void updateUserImage(UserDTO userDTO, byte[] image);

}
