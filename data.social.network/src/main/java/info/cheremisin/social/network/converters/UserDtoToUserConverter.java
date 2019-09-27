package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDtoToUserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .sex(Gender.getGenderByName(userDTO.getSex()))
                .blocked(userDTO.getBlocked())
                .image(userDTO.getImage())
                .dob(userDTO.getDob())
                .roles(new ArrayList<>())
                .build();
    }
}
