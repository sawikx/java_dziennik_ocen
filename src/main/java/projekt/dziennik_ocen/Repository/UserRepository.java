package projekt.dziennik_ocen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.dziennik_ocen.model.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findByRoles_Name(String roleName);
}
