package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Friendship;
import info.cheremisin.social.network.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {

    List<Friendship> findAllByUserSenderIdOrUserReceiverId(Long userSenderId, Long userReceiverId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Friendship f WHERE (f.userSender = :userId AND f.userReceiver = :friendId) " +
            "OR (f.userSender = :friendId AND f.userReceiver = :userId)")
    void deleteFriendRequests(User userId, User friendId);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into friendship (user_sender, user_receiver, accepted) VALUES (:user, :friend, true)",
            nativeQuery = true)
    void addFriendship(User user, User friend);
}
