package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value="SELECT m FROM Message m WHERE m.id IN (SELECT MAX(id) FROM Message GROUP BY sender, receiver) " +
            " AND (m.sender.id = :id OR m.receiver.id = :id)")
    List<Message> findAllRecentMessages(@Param("id") Long id);

    @Query(value="SELECT m FROM Message m WHERE (m.sender.id = :userId AND m.receiver.id = :companionId) OR " +
            "(m.sender.id = :companionId AND m.receiver.id = :userId) ORDER BY m.time")
    List<Message> findConversation(@Param("userId") Long userId, @Param("companionId") Long companionId);

    Message findFirstBySenderIdOrReceiverIdOrderByIdDesc(Long userId, Long theSameUserId);
}
