package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Friendship;
import org.springframework.data.repository.CrudRepository;

public interface FriendshipRepository extends CrudRepository<Friendship, Integer> {
}
