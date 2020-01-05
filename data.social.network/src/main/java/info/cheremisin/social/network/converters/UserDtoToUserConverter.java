package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoToUserConverter implements Converter<UserDTO, User> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User convert(UserDTO userDTO) {
        User.UserBuilder builder = User.builder()
                                       .id(userDTO.getId())
                                       .email(userDTO.getEmail())
                                       .firstName(userDTO.getFirstName())
                                       .lastName(userDTO.getLastName())
                                       .phone(userDTO.getPhone())
                                       .sex(Gender.getGenderByName(userDTO.getSex()))
                                       .image(userDTO.getImage())
                                       .dob(userDTO.getDob());

        if (userDTO.getPassword() != null)
            builder.password(passwordEncoder.encode(userDTO.getPassword()));

        return builder.build();
    }
}
