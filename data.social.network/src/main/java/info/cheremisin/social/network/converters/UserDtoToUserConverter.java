package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDTO, User> {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User.UserBuilder builder = User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .sex(Gender.getGenderByName(userDTO.getSex()))
                .blocked(userDTO.getBlocked())
                .image(userDTO.getImage())
                .dob(userDTO.getDob());
        if(userDTO.getPassword() != null) {
            String password = passwordEncoder.encode(userDTO.getPassword());
            builder.password(password);
        }
        return builder.build();
    }
}
