package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.domain.security.Role;

/**
 * todo Document type UserService
 */
public interface RoleService {

    Role getRole(long i) throws EntityNoExistsException;

    Role getRoleByName(String role) throws EntityNoExistsException;
}
