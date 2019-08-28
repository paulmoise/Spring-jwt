package com.pmtech.entifribackend.controller;

import java.util.List;

import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.exception.SpecialiteNotFoundException;
import com.pmtech.entifribackend.service.SpecialiteService;

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
 * SpecialiteController
 */
@RestController
@RequestMapping("/specialites")
public class SpecialiteController {

    @Autowired private SpecialiteService specialiteService;


    @GetMapping("")
    public ResponseEntity<List<Specialite>> getSpecialites(){
        return new  ResponseEntity<List<Specialite>>(specialiteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codSpe}")
    public ResponseEntity<Specialite> getSpecialite(@PathVariable String codSpe){
        Specialite specialite = specialiteService.findById(codSpe);
        if(specialite != null) return new ResponseEntity<Specialite>(specialite, HttpStatus.OK);
        else throw new SpecialiteNotFoundException(codSpe);
    }

    @PostMapping("")
    public ResponseEntity<Specialite> saveSpecialite(@RequestBody Specialite spe){
        return new ResponseEntity<>(specialiteService.save(spe),HttpStatus.OK);
    }

    @PutMapping("/{codSpe}")
    public ResponseEntity<Specialite> updateSpecialite(@PathVariable String codSpe, @RequestBody Specialite spe){
        spe.setCodSpe(codSpe);
        return new ResponseEntity<Specialite>(specialiteService.update(spe),HttpStatus.OK);
    }

    @DeleteMapping("/{codSpe}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable String codSpe, @RequestBody Specialite spe){
        spe.setCodSpe(codSpe);
        specialiteService.delete(spe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    
}