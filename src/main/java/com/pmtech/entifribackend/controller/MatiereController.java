package com.pmtech.entifribackend.controller;


import java.util.List;
import java.util.Optional;


import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.exception.MatiereNotFoundException;
import com.pmtech.entifribackend.exception.SpecialiteNotFoundException;

import com.pmtech.entifribackend.exception.UtilisateurServiceException;
import com.pmtech.entifribackend.repository.NiveauEtudeRepository;
import com.pmtech.entifribackend.service.EnseignantService;

import com.pmtech.entifribackend.service.MatiereService;
import com.pmtech.entifribackend.service.SpecialiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MatiereController
 */
@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired private MatiereService matiereService;
    @Autowired private EnseignantService enseignantService;
    @Autowired private SpecialiteService specialiteService;
    @Autowired private NiveauEtudeRepository niveauEtudeRepository;


    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Matiere>> getMatieres(){
        return new ResponseEntity<>(matiereService.findAll(),HttpStatus.OK);
    }

     @GetMapping("/searchByTeacher")
    public ResponseEntity<List<Matiere>> getMatieresByEnseignant(@RequestParam Long id){
        Enseignant enseignant = enseignantService.findById(id);
        if(enseignant != null)
            return new ResponseEntity<>(matiereService.findMatieresByEnseignant(enseignant),HttpStatus.OK);
        else throw new UtilisateurServiceException("User not found");
    }

    @GetMapping("/searchBySpecialiteAndNiveauEtude")
    public ResponseEntity<List<Matiere>> getMatieresBySpecialiteAndNiveauEtude(@RequestParam String codSpe,
    @RequestParam Long idNivEtu){
        Specialite spe = specialiteService.findById(codSpe);
        Optional<NiveauEtude> optnivEtu = niveauEtudeRepository.findById(idNivEtu);        
        if(spe != null && optnivEtu.isPresent())
            return new ResponseEntity<>(
                matiereService.findMatieresBySpecialiteAndNiveauEtude(spe, optnivEtu.get()), HttpStatus.OK);
        else
            throw new SpecialiteNotFoundException(codSpe);
    }

    @PostMapping("/")
    public ResponseEntity<Matiere> saveMatiere(@RequestBody Matiere matiere){
        Matiere dbmatiere = matiereService.save(matiere);
        if(dbmatiere != null) return new ResponseEntity<>(matiere, HttpStatus.OK);
        else throw new MatiereNotFoundException("not saved matiere"); 
    }

    @GetMapping("/{codMat}/")
    public ResponseEntity<Matiere> getMatiere(@PathVariable String codMat){
        System.err.println(codMat);
        Matiere matiere = matiereService.findById(codMat);
        if(matiere != null) return new ResponseEntity<>(matiere, HttpStatus.OK);
        else throw new MatiereNotFoundException(codMat); 
    }
    @PutMapping("/{codMat}/")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable String codMat, @RequestBody Matiere m){
        m.setCodMat(codMat);
        Matiere matiere = matiereService.update(m);
        if(matiere != null) return new ResponseEntity<>(matiere, HttpStatus.OK);
        else throw new MatiereNotFoundException(codMat); 
    }

    @DeleteMapping("/{codMat}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable String codMat){
        
        Matiere matiere = matiereService.findById(codMat);
        if(matiere != null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else throw new MatiereNotFoundException(codMat); 
    } 



    
}