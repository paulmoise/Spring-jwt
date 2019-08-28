package com.pmtech.entifribackend.controller;

import java.util.ArrayList;
import java.util.List;


import com.pmtech.entifribackend.domain.Response;
import com.pmtech.entifribackend.dto.EnseignantMatiereDto;
import com.pmtech.entifribackend.entities.Enseignant;
import com.pmtech.entifribackend.entities.Matiere;
import com.pmtech.entifribackend.exception.MatiereNotFoundException;
import com.pmtech.entifribackend.exception.UtilisateurServiceException;
import com.pmtech.entifribackend.service.EnseignantService;
import com.pmtech.entifribackend.service.MatiereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EnseignantController
 */
@RestController
@RequestMapping("/teachers")
public class EnseignantController {

    @Autowired private MatiereService matiereService;
    @Autowired private EnseignantService enseignantService;

    @GetMapping("/")
    public ResponseEntity<List<Enseignant>> getEnseignants(){
        return new ResponseEntity<>(enseignantService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{mtle}")
    public ResponseEntity<Enseignant> getEnseignantByMatricule(@PathVariable("mtle") Long mtle){
        Enseignant enseignant = enseignantService.findByMtle(mtle);
        if(enseignant != null)
            return new ResponseEntity<>(enseignant,HttpStatus.OK);
        else throw new UtilisateurServiceException("User not found");
    }

    @GetMapping("/{id}/matieres")
    public ResponseEntity<List<Matiere>> getMatieresByEnseignant(@PathVariable Long id){
        Enseignant enseignant = enseignantService.findById(id);
        if(enseignant != null)
            return new ResponseEntity<List<Matiere>>(matiereService.findMatieresByEnseignant(enseignant),HttpStatus.OK);
        else throw new UtilisateurServiceException("User not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getMatiere(@PathVariable Long id){
        Enseignant enseignant = enseignantService.findById(id);
        if(enseignant != null)
            return new ResponseEntity<>(enseignant,HttpStatus.OK);
        else throw new UtilisateurServiceException("User not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant){
        enseignant.setId(id);
        Enseignant dbenseignant= enseignantService.update(enseignant);
        if(dbenseignant != null)
            return new ResponseEntity<>(dbenseignant,HttpStatus.OK);
        else throw new UtilisateurServiceException("User not found");
    }



    /*@PutMapping("/{id}/matieres/")
    public ResponseEntity<Enseignant> affectMatiereToTeacher(@PathVariable Long id, @RequestBody EnseignantMatiereDto dto){
        Enseignant enseignant =enseignantService.findById(id);
        enseignant.setId(id);
        enseignant.setGrade(dto.getGrade());
        enseignant.setNumMtle(dto.getNumMtle());
        enseignant.setNom(dto.getNom());
        enseignant.setPrenom(dto.getPrenom());
        enseignant.setLogin(dto.getLogin());
        enseignant.setCIN(dto.getCIN());
        enseignant.setActive(dto.isActive());
        enseignant.setEmail(dto.getEmail());
        enseignant.setLieuNaissance(dto.getLieuNaissance());
        enseignant.setDateNaissance(dto.getDateNaissance());
        enseignant.setSexe(dto.getSexe());
        enseignant.setPassword(dto.getPassword());
        enseignant.setDefaultPassword(dto.getDefaultPassword());
        Enseignant updateEnseignant = null;
        List<Matiere> matieres = new ArrayList<>();

        if(enseignant != null){
            for(String codMat : dto.getTabCodMat()){
                Matiere m = matiereService.findById(codMat);
                m.setCodMat(codMat);
                System.err.println("avant"+m.toString());
                if( m!= null) {
                    m.setEnseignant(enseignant);
                    Matiere dbMatiere = matiereService.update(m);
                    System.err.println("after"+dbMatiere.toString());
                    matieres.add(dbMatiere);
                }
                else throw new MatiereNotFoundException(codMat);
            }
            System.err.println("Enseignant avant"+enseignant.toString());
                enseignant.setMatieres(matieres);
               updateEnseignant =  enseignantService.update(enseignant);
            System.err.println("Enseignant avant"+updateEnseignant.toString());
            return new ResponseEntity<> (updateEnseignant, HttpStatus.OK);
        }else throw new UtilisateurServiceException("User not found");

    }*/

    @PutMapping("/{id}/matieres")
    public ResponseEntity<Response> affectMatiereToTeacher(@PathVariable("id") Long id, @RequestBody EnseignantMatiereDto dto){
        Enseignant enseignant =enseignantService.findById(dto.getId());
        if(enseignant != null){
            if(dto.getTabCodMat() != null && dto.getTabCodMat().length > 0) {
                for (String codMat : dto.getTabCodMat()) {
                    Matiere m = matiereService.findById(codMat);
                    if (m != null) {
                        m.setEnseignant(enseignant);
                        matiereService.update(m);
                    } else throw new MatiereNotFoundException(codMat);
                }
            }else throw new UtilisateurServiceException("No matiere");
        }else throw new UtilisateurServiceException("User not found");

        return new ResponseEntity<> (new Response("User updated"), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Enseignant> saveEnseignant(@RequestBody Enseignant ens){
        return new ResponseEntity<Enseignant>(enseignantService.save(ens), HttpStatus.OK);
    }
    
}