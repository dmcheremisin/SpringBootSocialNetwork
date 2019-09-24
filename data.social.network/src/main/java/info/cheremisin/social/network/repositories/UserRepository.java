package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
}
