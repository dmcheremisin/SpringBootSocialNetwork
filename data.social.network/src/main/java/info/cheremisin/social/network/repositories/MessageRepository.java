package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Message;
import info.cheremisin.social.network.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
