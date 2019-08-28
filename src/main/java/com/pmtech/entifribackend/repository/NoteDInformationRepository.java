package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * NoteDInformationRepository
 */
@RepositoryRestResource
public interface NoteDInformationRepository extends InformationBaseRepository<NoteDInformation>{

    List<NoteDInformation> findAllByDestinataire(int destinataire);
    List<NoteDInformation> findAllBySpecialites(Specialite specialite);
    List<NoteDInformation> findAllByNiveauEtudes(NiveauEtude niveauEtude);
    List<NoteDInformation> findAllBySpecialitesAndNiveauEtudes(Specialite specialite, NiveauEtude niveauEtude);
    List<NoteDInformation> findAllByEnseignants(List<Enseignant> enseignants);
    List<NoteDInformation> findAllByEtudiants(List<Etudiant> etudiants);
    List<NoteDInformation> findAllByDatPost(Date datPost);

}