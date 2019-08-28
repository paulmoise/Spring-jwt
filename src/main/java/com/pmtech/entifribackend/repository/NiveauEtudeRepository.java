package com.pmtech.entifribackend.repository;


import com.pmtech.entifribackend.entities.NiveauEtude;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * NiveauEtudeRepository
 */
@RepositoryRestResource
public interface NiveauEtudeRepository extends CrudRepository<NiveauEtude,Long>{    
}