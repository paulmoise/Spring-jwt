package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * AppUserBaseRepository
 */
@NoRepositoryBean
public interface AppUserBaseRepository<T extends AppUser> extends JpaRepository<T, Long> {
}