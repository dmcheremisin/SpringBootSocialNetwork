package info.cheremisin.social.network.bootstrap;

import info.cheremisin.social.network.entities.Friendship;
import info.cheremisin.social.network.entities.Message;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.repositories.FriendshipRepository;
import info.cheremisin.social.network.repositories.MessageRepository;
import info.cheremisin.social.network.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootstrapDb implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final FriendshipRepository friendshipRepository;

    public BootstrapDb(UserRepository userRepository,
                       MessageRepository messageRepository,
                       FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User user = new User();
        user.setFirstName("Dima");
        user.setLastName("Cheremisin");
        user.setDob(new Date());

        userRepository.save(user);

        Message message = new Message();
        message.setMessage("sasf");
        message.setReceiver(user);
        message.setSender(user);

        messageRepository.save(message);

        Friendship friendship = new Friendship();
        friendship.setAccepted(false);
        friendship.setUserSender(user);
        friendship.setUserReceiver(user);

        friendshipRepository.save(friendship);
    }

}
