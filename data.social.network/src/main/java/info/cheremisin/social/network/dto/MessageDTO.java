package info.cheremisin.social.network.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private LocalDateTime time;
    private String message;
    private UserDTO sender;
    private UserDTO receiver;
    private Long companionId;
}
