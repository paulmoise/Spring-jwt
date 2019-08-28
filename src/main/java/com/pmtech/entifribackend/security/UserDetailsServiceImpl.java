package com.pmtech.entifribackend.security;

import com.pmtech.entifribackend.entities.AppUser;
import com.pmtech.entifribackend.repository.AppUserRepository;
import com.pmtech.entifribackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired private AccountService accountService;

    @Autowired private AppUserRepository appUserRepository;

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format("User with '%s' not found", username));
        }else{
            return JwtUserFactory.create(user);
        }

    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUserName(username);
        if (appUser==null){
            throw new UsernameNotFoundException(String.format("User with '%s' not found", username));
        }else return JwtUserFactory.create(appUser);

        /*Collection<GrantedAuthority> authorities=new ArrayList<>();

        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });*/


    }
}
