package info.cheremisin.social.network.bootstrap;

import info.cheremisin.social.network.entities.Friendship;
import info.cheremisin.social.network.entities.Message;
import info.cheremisin.social.network.entities.Role;
import info.cheremisin.social.network.entities.User;
import info.cheremisin.social.network.repositories.FriendshipRepository;
import info.cheremisin.social.network.repositories.MessageRepository;
import info.cheremisin.social.network.repositories.RoleRepository;
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
    private final RoleRepository roleRepository;

    public BootstrapDb(UserRepository userRepository, MessageRepository messageRepository,
                       FriendshipRepository friendshipRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.friendshipRepository = friendshipRepository;
        this.roleRepository = roleRepository;
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

        Role admin = new Role();
        admin.setName("ADMIN");
        Role userRole = new Role();
        userRole.setName("USER");

        roleRepository.save(admin);
        roleRepository.save(userRole);

    }

}
