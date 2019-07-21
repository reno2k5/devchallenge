
package com.studentsco.store.security;

import com.studentsco.store.model.security.User;
import com.studentsco.store.repositories.UsersJPARepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class StoreUserDetailsService implements UserDetailsService{

    @Autowired
    private UsersJPARepository userRepo;
    
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        //return new StoreUserPrincipal(user);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                getAuthorities(user));
    } 

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return authorities;
    }

}