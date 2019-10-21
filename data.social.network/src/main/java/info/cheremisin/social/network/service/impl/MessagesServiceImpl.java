package info.cheremisin.social.network.service.impl;

import info.cheremisin.social.network.converters.MessageToMessageDtoConverter;
import info.cheremisin.social.network.dto.MessageDTO;
import info.cheremisin.social.network.entities.Message;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.repositories.MessageRepository;
import info.cheremisin.social.network.service.MessagesService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessagesServiceImpl implements MessagesService {

    private MessageRepository messageRepository;
    private MessageToMessageDtoConverter messageConverter;

    public MessagesServiceImpl(MessageRepository messageRepository,
                               MessageToMessageDtoConverter messageConverter) {
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
    }

    public Collection<MessageDTO> findAllRecentMessages(Long id) {
        Iterable<Message> all = messageRepository.findAllRecentMessages(id);
        Map<User, MessageDTO> map = new HashMap<>();
        all.forEach(m -> {
            MessageDTO messageDTO = messageConverter.convert(m, id);
            User user = m.getSender().getId().equals(id) ? m.getReceiver() : m.getSender();
            map.put(user, messageDTO);
        });
        return map.values();
    }

    @Override
    public List<MessageDTO> findConversation(Long userId, Long companionId) {
        List<Message> all = messageRepository.findConversation(userId, companionId);
        List<MessageDTO> messages = new LinkedList<>();
        all.forEach(m -> messages.add(messageConverter.convert(m, userId)));
        return messages;
    }

    @Override
    public MessageDTO getRecentMessage(Long id) {
        Message message = messageRepository.findFirstBySenderIdOrReceiverIdOrderByIdDesc(id, id);
        MessageDTO messageDTO = messageConverter.convert(message, id);
        return messageDTO;
    }


}
