package com.pmtech.entifribackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.repository.SpecialiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SpecialiteService
 */
@Service
public class SpecialiteService {

    @Autowired private SpecialiteRepository specialiteRepository;

    public List<Specialite> findAll(){
        
        Iterable<Specialite> it = specialiteRepository.findAll();
        if(it instanceof List)
            return (List<Specialite>) specialiteRepository.findAll();
        return StreamSupport.stream(specialiteRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    }

    public Specialite findById(String spe){
        Optional<Specialite> optSpe = specialiteRepository.findById(spe);
        if(optSpe.isPresent())
            return optSpe.get();
        else return null;
    }

    public Specialite save(Specialite specialite){
        return specialiteRepository.save(specialite);
    }

    public void delete(Specialite spe){
         specialiteRepository.delete(spe);
    }
    
    public Specialite update(Specialite specialite){
        return specialiteRepository.save(specialite);
    }
}