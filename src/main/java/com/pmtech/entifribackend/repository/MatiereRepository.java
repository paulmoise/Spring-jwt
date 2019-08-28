package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;

import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * MatiereRepository
 */
@RepositoryRestResource
public interface MatiereRepository extends CrudRepository< Matiere, String>{
    
    List<Matiere> findByEnseignant(Enseignant enseignant);
    List<Matiere> findBySpecialiteAndNiveauEtude(Specialite spe, NiveauEtude nivEtu);
}