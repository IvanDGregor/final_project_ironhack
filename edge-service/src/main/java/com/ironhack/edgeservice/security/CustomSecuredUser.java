package com.ironhack.edgeservice.security;

import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomSecuredUser extends User implements UserDetails {
    private static final long serialVersionUID = -4381938875186527688L;
    private Role role;

    public CustomSecuredUser(User user) {
        System.out.println(this + "Hello 1");
        this.setRoles(user.getRoles());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>(); //Authority son los roles que tendrá nuestro user
        Set<Role> roles = this.getRoles();//cada user tiene un set que roles es eso que estamos definindo
        System.out.println(this);
        for (Role role : roles) {
            System.out.println(role.getRole());
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }
}