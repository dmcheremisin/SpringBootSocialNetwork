package info.cheremisin.social.network.service.impl;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.converters.PageToPageDtoUserConverter;
import info.cheremisin.social.network.converters.UserDtoToUserConverter;
import info.cheremisin.social.network.converters.UserToUserDtoConverter;
import info.cheremisin.social.network.dto.PageDTO;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.Role;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.exceptions.SocialNetworkException;
import info.cheremisin.social.network.exceptions.UserNotFoundException;
import info.cheremisin.social.network.repositories.RoleRepository;
import info.cheremisin.social.network.repositories.UserRepository;
import info.cheremisin.social.network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static info.cheremisin.social.network.constants.Constants.ROLE_ADMIN;
import static info.cheremisin.social.network.constants.Constants.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;
    private final PageToPageDtoUserConverter pageToPageDtoUserConverter;

    private static void checkSuperAdmin(User user) {
        if (user.getId() == 1)
            throw new SocialNetworkException("It is not allowed to change roles for the user with id = 1");
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() ->
                                     new UserNotFoundException(String.format("User with id = %s is not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = getUser(id);
        return userToUserDtoConverter.convert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userToUserDtoConverter.convert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO<UserDTO> findAllPageable(Long id, Pageable pageable) {
        Page<User> pagedUsers = userRepository.findAllByIdNot(id, pageable);
        return pageToPageDtoUserConverter.convert(pagedUsers);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO<UserDTO> findAllWithSearch(Long id, String search, Pageable pageable) {
        search = String.format("%%%s%%", search).toLowerCase();
        Page<User> pagedUsers = userRepository.findAllWithSearch(id, search, pageable);
        return pageToPageDtoUserConverter.convert(pagedUsers);
    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = userDtoToUserConverter.convert(userDTO);
        user.setRoles(new ArrayList<>());

        Role role = roleRepository.getRoleByName(ROLE_USER);
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(String password, Long id) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.updatePassword(encodedPassword, id);
    }

    @Override
    @Transactional
    public void updateUser(UserDTO user) {
        int gender = Gender.getGenderByName(user.getSex());
        userRepository.updateUserSettings(user.getFirstName(), user.getLastName(), user.getDob(), gender,
                user.getPhone(), user.getId());
    }

    @Override
    @Transactional
    public void updateUserImage(UserDTO userDTO, String fileName) {
        User user = getUser(userDTO.getId());
        user.setImage(fileName);
        userRepository.save(user);
        userDTO.setImage(fileName);
    }

    @Override
    @Transactional
    public void makeUserAdmin(Long userId) {
        User user = getUser(userId);
        checkSuperAdmin(user);
        Role role = roleRepository.getRoleByName(ROLE_ADMIN);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void blockUser(Long userId) {
        User user = getUser(userId);
        checkSuperAdmin(user);
        user.setRoles(new ArrayList<>());
        userRepository.save(user);
    }
}
