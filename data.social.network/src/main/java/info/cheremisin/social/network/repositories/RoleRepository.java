package info.cheremisin.social.network.repositories;

import info.cheremisin.social.network.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role getRoleByName(String name);
}
