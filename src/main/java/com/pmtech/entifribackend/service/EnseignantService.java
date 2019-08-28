package com.pmtech.entifribackend.service;

import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;
import com.pmtech.entifribackend.repository.EnseignantRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * EnseignantService
 */
@Service
@Transactional
public class EnseignantService {

    @Autowired private EnseignantRepository eRepository;
    public Enseignant save(Enseignant enseignant){
        return eRepository.save(enseignant);
    }

    public Enseignant findById(Long id){
        if( eRepository.findById(id).isPresent())
            return eRepository.findById(id).get();
        else return null;
    }

    public Enseignant getOne(Long id){
        Enseignant ens = eRepository.getOne(id);
            return ens ;
    }

    public Enseignant update(Enseignant enseignant){
        System.err.println("teacher update work");
        return eRepository.save(enseignant);
    }

    public void delete(Enseignant enseignant){
        eRepository.delete(enseignant);
    }


    public List<Enseignant> findAll() {
        return eRepository.findAll();
    }

    public Enseignant findByMtle(Long mtle) {
        return eRepository.findByNumMtle(mtle);
    }
}