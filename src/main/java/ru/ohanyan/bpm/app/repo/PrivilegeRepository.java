package ru.ohanyan.bpm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.domain.security.Privilege;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByPrivilegeName(String name);

    boolean existsByPrivilegeName(String name);
}
