package info.cheremisin.social.network.converters;

import info.cheremisin.social.network.dto.MessageDTO;
import info.cheremisin.social.network.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageToMessageDtoConverter {

    private UserToUserDtoConverter userToUserDtoConverter;

    public MessageToMessageDtoConverter(UserToUserDtoConverter userToUserDtoConverter) {
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public MessageDTO convert(Message message, Long id) {
        if(message == null) {
            return null;
        }
        MessageDTO messageDTO = MessageDTO.builder()
                .time(message.getTime())
                .message(message.getMessage())
                .receiver(userToUserDtoConverter.convert(message.getReceiver()))
                .sender(userToUserDtoConverter.convert(message.getSender()))
                .companionId(id.equals(message.getSender().getId()) ? message.getReceiver().getId() : message.getSender().getId())
                .build();
        return messageDTO;
    }
}
