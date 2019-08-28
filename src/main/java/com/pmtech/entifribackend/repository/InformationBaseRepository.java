package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.Information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * InformationBaseRepository
 */
@NoRepositoryBean
public interface InformationBaseRepository<T extends Information> extends JpaRepository<T, Long>{   
}