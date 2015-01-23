package listmatchs.security.repositories;

import listmatchs.security.entities.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

  Role findRoleByName(String name);

}
