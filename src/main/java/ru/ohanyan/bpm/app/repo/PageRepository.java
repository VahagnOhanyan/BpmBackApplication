package ru.ohanyan.bpm.app.repo;

/**
 * todo Document type PageRepository
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.domain.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    boolean existsByName(String name);
    Page findByName(String name);
   
}
