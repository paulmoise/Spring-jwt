package com.pmtech.entifribackend.repository;

import java.util.List;

import com.pmtech.entifribackend.entities.Etudiant;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;


/**
 * EtudiantRepository
 */
public interface EtudiantRepository extends AppUserBaseRepository<Etudiant> {

    Etudiant findByNumMtle(Long matricule);
    List<Etudiant> findBySpecialiteAndNiveauEtude(Specialite specialite, NiveauEtude nEtude);
    List<Etudiant> findByNiveauEtude(NiveauEtude niveauEtude);
    List<Etudiant> findBySpecialite(Specialite specialite);
    Etudiant findBySpecialiteAndNiveauEtudeAndNumMtle(Specialite spe,NiveauEtude nivEtu,String  numMtle);
    List<Etudiant> getEtudiantsByActiveTrue();
    List<Etudiant> findAllByNomContaining(String motCle);
}