package info.cheremisin.social.network.dto;

import info.cheremisin.social.network.validation.FieldMatch;
import info.cheremisin.social.network.validation.Password;
import info.cheremisin.social.network.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirmation", message = "The password fields must match")
})
public class UserDTO {

    private Long id;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    @Size(min=6, max=100, message = "Min size is 6 and max size is 100")
    @Password
    private String password;

    @NotNull
    @Size(min=6, max=100, message = "Min size is 6 and max size is 100")
    private String passwordConfirmation;

    @NotNull
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String firstName;

    @NotNull
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String lastName;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dob;

    private String sex;
    private String phone;
    private Boolean blocked;
    private Boolean hasImage;

}
