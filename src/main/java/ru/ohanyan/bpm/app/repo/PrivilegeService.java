package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.domain.security.Role;

public interface PrivilegeService {

    Role getRole(long i) throws EntityNoExistsException;

    Role getRoleByName(String role) throws EntityNoExistsException;
}
