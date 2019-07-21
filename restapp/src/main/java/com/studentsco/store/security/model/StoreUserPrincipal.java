package com.studentsco.store.security.model;

import com.studentsco.store.model.security.Role;
import com.studentsco.store.model.security.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StoreUserPrincipal implements UserDetails {

    Logger logger = LogManager.getLogger(StoreUserPrincipal.class);
    private final User user;
    
    public StoreUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList();
        user.getRoles().forEach((rol) -> {
            auth.add(new SimpleGrantedAuthority(rol.getRole()));
        });
        
        return auth;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

}
