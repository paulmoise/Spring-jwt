package com.pmtech.entifribackend.security;

import com.pmtech.entifribackend.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JwtUserFactory {

    public static UserDetails create(AppUser user) {

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new JwtUser(user.getId(),user.getUsername(), user.getPassword(),user,
                authorities , user.isActive());
    }

}
