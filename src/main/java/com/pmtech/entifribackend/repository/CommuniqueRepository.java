package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * CommuniqueRepository
 */
@RepositoryRestResource
public interface CommuniqueRepository extends InformationBaseRepository<Communique> {

    List<Communique> findAllByDestinataire(int destinataire);
    List<Communique> findAllBySpecialites(Specialite specialite);
    List<Communique> findAllByNiveauEtudes(NiveauEtude niveauEtude);
    List<Communique> findAllBySpecialitesAndNiveauEtudes(Specialite specialite, NiveauEtude niveauEtude);
    List<Communique> findAllByEnseignants(List<Enseignant> enseignants);
    List<Communique> findAllByEtudiants(List<Etudiant> etudiants);
    List<Communique> findAllByDatPost(Date datPost);
}