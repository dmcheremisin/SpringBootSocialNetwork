package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value="SELECT m FROM Message m WHERE m.id IN (SELECT MAX(id) FROM Message GROUP BY sender, receiver) " +
            " AND (m.sender.id = :id OR m.receiver.id = :id)")
    List<Message> findAllRecentMessages(Long id);

    @Query(value="SELECT m FROM Message m WHERE (m.sender.id = :userId AND m.receiver.id = :companionId) OR " +
            "(m.sender.id = :companionId AND m.receiver.id = :userId) ORDER BY m.time")
    List<Message> findConversation(Long userId, Long companionId);

    //@Query(value="SELECT m FROM Message m WHERE m.id = :id ORDER BY m.time DESC LIMIT 1")
    Message findFirstBySenderIdOrReceiverIdOrderByIdDesc(Long id, Long id1);
}
