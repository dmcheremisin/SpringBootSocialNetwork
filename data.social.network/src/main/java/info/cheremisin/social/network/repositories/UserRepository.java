package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findUserByEmail(String email);

    Page<User> findAllByIdNot(Long Id,  Pageable pageable);

    @Query(value="SELECT u FROM User u WHERE u.id <> :id AND ( LOWER(u.firstName) LIKE :search OR LOWER(u.lastName) LIKE :search )")
    Page<User> findAllWithSearch(@Param("id") Long id, @Param("search") String search, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName," +
            "u.dob = :dob, u.sex = :sex, u.phone = :phone WHERE u.id = :id")
    int updateUserSettings(@Param("firstName") String firstName, @Param("lastName") String lastName,
                   @Param("dob") LocalDate dob, @Param("sex") Integer sex, @Param("phone") String phone,
                   @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    int updatePassword(@Param("password") String password, @Param("id") Long id);
}
