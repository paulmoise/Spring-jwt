package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * EmploiDuTempsRepository
 */
@RepositoryRestResource
public interface EmploiDuTempsRepository extends InformationBaseRepository<EmploiDuTemps> {
    List<EmploiDuTemps> findAllByDestinataire(int destinataire);
    List<EmploiDuTemps> findAllBySpecialites(Specialite specialite);
    List<EmploiDuTemps> findAllByNiveauEtudes(NiveauEtude niveauEtude);
    List<EmploiDuTemps> findAllBySpecialitesAndNiveauEtudes(Specialite specialite, NiveauEtude niveauEtude);
    List<EmploiDuTemps> findAllByEnseignants(List<Enseignant> enseignants);
    List<EmploiDuTemps> findAllByEtudiants(List<Etudiant> etudiants);
    List<EmploiDuTemps> findAllByDatPost(Date datPost);
}