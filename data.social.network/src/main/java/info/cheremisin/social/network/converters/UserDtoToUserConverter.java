package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserDtoToUserConverter implements Converter<UserDTO, User> {
    public static final SimpleDateFormat SHORT_DT = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public User convert(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setSex(Gender.getGenderByName(userDTO.getSex()));

        try {
            Date date = SHORT_DT.parse(userDTO.getDob());
            user.setDob(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setBlocked(userDTO.getBlocked());
        user.setImage(userDTO.getImage());
        return user;
    }
}
