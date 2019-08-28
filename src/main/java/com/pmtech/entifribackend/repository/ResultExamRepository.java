package com.pmtech.entifribackend.repository;

import com.pmtech.entifribackend.entities.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * ResultExamRepository
 */
@RepositoryRestResource
public interface ResultExamRepository extends InformationBaseRepository<ResultExam> {

    List<ResultExam> findAllByDestinataire(int destinataire);
    List<ResultExam> findAllBySpecialites(Specialite specialite);
    List<ResultExam> findAllByNiveauEtudes(NiveauEtude niveauEtude);
    List<ResultExam> findAllBySpecialitesAndNiveauEtudes(Specialite specialite, NiveauEtude niveauEtude);
    List<ResultExam> findAllByEnseignants(List<Enseignant> enseignants);
    List<ResultExam> findAllByEtudiants(List<Etudiant> etudiants);
    List<ResultExam> findAllByDatPost(Date datPost);

    
}