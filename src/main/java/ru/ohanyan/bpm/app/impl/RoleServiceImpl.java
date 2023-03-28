package ru.ohanyan.bpm.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.repo.RoleRepository;
import ru.ohanyan.bpm.app.repo.RoleService;
import ru.ohanyan.bpm.domain.security.Role;

/**
 * todo Document type RoleServiceImpl
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRole(long i) throws EntityNoExistsException {
        if (roleRepository.existsById(i)) {
            return roleRepository.getById(i);
        } else {
            throw new EntityNoExistsException("User doesn't exist");
        }
    }

    @Override
    public Role getRoleByName(String role) throws EntityNoExistsException {
        if (roleRepository.existsByRoleName(role)) {
            return roleRepository.findByRoleName(role);
        } else {
            throw new EntityNoExistsException("User doesn't exist");
        }
    }
}
