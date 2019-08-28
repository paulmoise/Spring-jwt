package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;

/**
 * EnseignantRepository
 */
public interface EnseignantRepository extends AppUserBaseRepository<Enseignant> {

    Enseignant findByMatieresIsContaining(Matiere matiere);

    Enseignant findByNumMtle(Long mtle);
}