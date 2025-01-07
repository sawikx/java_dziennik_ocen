package projekt.dziennik_ocen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.dziennik_ocen.model.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
