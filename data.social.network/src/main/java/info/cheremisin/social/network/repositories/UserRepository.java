package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName," +
            "u.dob = :dob, u.sex = :sex, u.phone = :phone WHERE u.id = :id")
    int updateUserSettings(@Param("firstName") String firstName, @Param("lastName") String lastName,
                   @Param("dob") LocalDate dob, @Param("sex") Integer sex, @Param("phone") String phone,
                   @Param("id") Long id);
}
