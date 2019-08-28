package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.AppUser;

/**
 * AppUserRepository
 */
public interface AppUserRepository extends AppUserBaseRepository<AppUser> {
    public AppUser findByUsername(String username);

}