package info.cheremisin.social.network.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private LocalDateTime time;

    @NotNull
    @Size(min=3, max = 3000)
    private String message;
    private UserDTO sender;
    private UserDTO receiver;
    private Long companionId;
}
