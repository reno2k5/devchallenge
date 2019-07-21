package com.studentsco.store.repositories;

import com.studentsco.store.dao.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleJPARepository extends CrudRepository<Role, Integer>{
    Role findByRole(String role);
}
