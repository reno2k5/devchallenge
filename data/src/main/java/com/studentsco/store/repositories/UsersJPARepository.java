package com.studentsco.store.repositories;

import com.studentsco.store.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersJPARepository extends CrudRepository<User, Integer>{
    
    public User findByUsername(String username);
}
