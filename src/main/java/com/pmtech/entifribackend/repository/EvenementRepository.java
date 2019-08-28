package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.*;

import java.util.Date;
import java.util.List;

/**
 * EvenementRepository
 */
public interface EvenementRepository extends InformationBaseRepository<Evenement>{

    List<Evenement> findAllByDestinataire(int destinataire);
    List<Evenement> findAllBySpecialites(Specialite specialite);
    List<Evenement> findAllByNiveauEtudes(NiveauEtude niveauEtude);
    List<Evenement> findAllBySpecialitesAndNiveauEtudes(Specialite specialite, NiveauEtude niveauEtude);
    List<Evenement> findAllByEnseignants(List<Enseignant> enseignants);
    List<Evenement> findAllByEtudiants(List<Etudiant> etudiants);
    List<Evenement> findAllByDatPost(Date datPost);
    
}