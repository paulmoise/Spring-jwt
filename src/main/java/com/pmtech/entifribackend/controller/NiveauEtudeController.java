package com.pmtech.entifribackend.controller;

import java.util.List;
import java.util.Optional;

import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.exception.NiveauEtudeNotFoundException;
import com.pmtech.entifribackend.repository.NiveauEtudeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NiveauEtudeController
 */
@RestController
@RequestMapping("/niveauEtudes")
public class NiveauEtudeController {

    @Autowired private NiveauEtudeRepository nEtudeRepository;


    @GetMapping("")
    public ResponseEntity<List<NiveauEtude>> getNiveauEtudes(){
        return new  ResponseEntity<List<NiveauEtude>>((List<NiveauEtude>)nEtudeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idNivEtu}")
    public ResponseEntity<NiveauEtude> getNiveauEtude(@PathVariable Long idNivEtu){
       Optional<NiveauEtude> optniveauEtude = nEtudeRepository.findById(idNivEtu);
        if(optniveauEtude.isPresent()) return new ResponseEntity<NiveauEtude>(optniveauEtude.get(), HttpStatus.OK);
        else throw new NiveauEtudeNotFoundException(idNivEtu);
    }

    @PostMapping("")
    public ResponseEntity<NiveauEtude> saveNiveauEtude(@RequestBody NiveauEtude nEtude){
        return new ResponseEntity<>(nEtudeRepository.save(nEtude),HttpStatus.CREATED);
    }

    @PutMapping("/{idNivEtu}")
    public ResponseEntity<NiveauEtude> updateSpecialite(@PathVariable Long idNivEtu, @RequestBody NiveauEtude nEtude){
        nEtude.setIdNivEtu(idNivEtu);
        return new ResponseEntity<NiveauEtude>(nEtudeRepository.save(nEtude),HttpStatus.OK);
    }

    @DeleteMapping("/{idNivEtu}")
    public ResponseEntity<Void> deleteNiveauEtude(@PathVariable Long idNivEtu){
        Optional<NiveauEtude> optniveauEtude = nEtudeRepository.findById(idNivEtu);
        if(optniveauEtude.isPresent()){
            nEtudeRepository.delete(optniveauEtude.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else throw new NiveauEtudeNotFoundException(idNivEtu);
        
        
    }



    
}