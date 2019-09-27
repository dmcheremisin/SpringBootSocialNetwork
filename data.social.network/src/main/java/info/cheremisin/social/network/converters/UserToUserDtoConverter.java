package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.constants.Gender;
import info.cheremisin.social.network.dto.UserDTO;
import info.cheremisin.social.network.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDTO> {
    private static final ThreadLocal<SimpleDateFormat> SHORT_DT = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    @Override
    public UserDTO convert(User user) {
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setSex(Gender.getGenderById(user.getSex()).name());

        Date dob = user.getDob();
        if(dob != null){
            String dateString = SHORT_DT.get().format(dob);
            userDTO.setDob(dateString);
        }

        userDTO.setBlocked(user.getBlocked());
        userDTO.setImage(user.getImage());
        return userDTO;
    }
}
