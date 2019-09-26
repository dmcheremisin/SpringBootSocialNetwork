package info.cheremisin.social.network.service.impl;

import info.cheremisin.social.network.converters.UserDtoToUserConverter;
import info.cheremisin.social.network.converters.UserToUserDtoConverter;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.Role;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.repositories.UserRepository;
import info.cheremisin.social.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static info.cheremisin.social.network.constants.Constants.ROLE_USER;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserConverter userDtoToUserConverter;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserToUserDtoConverter userToUserDtoConverter,
                           UserDtoToUserConverter userDtoToUserConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        UserDTO userDTO = userToUserDtoConverter.convert(user);
        return userDTO;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = userDtoToUserConverter.convert(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = new Role();
        role.setName(ROLE_USER);
        user.getRoles().add(role);

        userRepository.save(user);
    }
}
