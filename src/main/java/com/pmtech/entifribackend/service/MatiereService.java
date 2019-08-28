package com.pmtech.entifribackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.repository.MatiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * MatiereService
 */
@Service
public class MatiereService {

    @Autowired private MatiereRepository matiereRepository;


    public List<Matiere> findAll(){
        
       /* Iterable<Matiere> it = matiereRepository.findAll();
        if(it instanceof List)
            return (List<Matiere>) matiereRepository.findAll();
        return StreamSupport.stream(matiereRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
        */
        return (List<Matiere>) matiereRepository.findAll();
    }

    public List<Matiere> findMatieresByEnseignant(Enseignant enseignant){
        return matiereRepository.findByEnseignant(enseignant);
    }

    public List<Matiere> findMatieresBySpecialiteAndNiveauEtude(Specialite spe,NiveauEtude nivEtu){
        return matiereRepository.findBySpecialiteAndNiveauEtude(spe, nivEtu);
    }

    public Matiere findById(String codMat){
        Optional<Matiere> optMatiere = matiereRepository.findById(codMat);
        if(optMatiere.isPresent())
            return optMatiere.get();
        else return null;
    }

    public Matiere save(Matiere matiere){
        return matiereRepository.save(matiere);
    }

    public void delete(Matiere matiere){
         matiereRepository.delete(matiere);
    }
    
    public Matiere update(Matiere matiere){
        return matiereRepository.save(matiere);
    }

}