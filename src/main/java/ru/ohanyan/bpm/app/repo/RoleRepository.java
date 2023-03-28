package ru.ohanyan.bpm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.domain.security.Role;

/**
 * todo Document type UserRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    boolean existsByRoleName(String name);
}
