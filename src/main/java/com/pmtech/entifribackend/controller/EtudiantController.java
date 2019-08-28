package com.pmtech.entifribackend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmtech.entifribackend.entities.Etudiant;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.exception.MatiereNotFoundException;
import com.pmtech.entifribackend.exception.NiveauEtudeNotFoundException;
import com.pmtech.entifribackend.exception.UtilisateurServiceException;
import com.pmtech.entifribackend.repository.NiveauEtudeRepository;
import com.pmtech.entifribackend.service.EtudiantService;
import com.pmtech.entifribackend.service.SpecialiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * EtudiantController
 */
@RestController
@RequestMapping("/students")
public class EtudiantController {

    @Autowired private EtudiantService eService;
    @Autowired private SpecialiteService specialiteService;
    @Autowired private NiveauEtudeRepository niveauEtudeRepository;

    @Value("${dir.basePath}")
    private String basePath;


    @GetMapping(path = "/", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Etudiant>> getEtudiants(){
            return new ResponseEntity<List<Etudiant>>(eService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/niveauEtudes/{idNivEtu}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByNiveauEtude(@PathVariable Long idNivEtu){
        Optional<NiveauEtude> optnivEtu = niveauEtudeRepository.findById(idNivEtu);        
        if(optnivEtu.isPresent())
            return new ResponseEntity<List<Etudiant>>(eService.findByNiveauEtude(optnivEtu.get()), HttpStatus.OK);
        else
            throw new NiveauEtudeNotFoundException(idNivEtu);
    }

    

    @GetMapping(path = "/specialites/{codSpe}")
    public ResponseEntity<List<Etudiant>> getEtudiantsBySpecialite(@PathVariable String codSpe){
        Specialite spe = specialiteService.findById(codSpe);
        if(spe != null)
            return new ResponseEntity<List<Etudiant>>(eService.findBySpecialite(spe), HttpStatus.OK);
        else
            throw new MatiereNotFoundException(codSpe);
    }

    @GetMapping(path = "/{numMtle}")
    public ResponseEntity<Etudiant> getEtudiantsByMtle(@PathVariable Long numMtle){
        Etudiant etudiant = eService.findByNumMtle(numMtle);
        if(etudiant != null)
            return new ResponseEntity<Etudiant>(etudiant, HttpStatus.OK);
        else
            throw new MatiereNotFoundException(" "+numMtle);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Etudiant>> getEtudiantsBySpecialiteAndNiveauEtude(@RequestParam("codSpe") String codSpe,
    @RequestParam("idNivEtu") Long idNivEtu){
        Specialite spe = specialiteService.findById(codSpe);
        Optional<NiveauEtude> optnivEtu = niveauEtudeRepository.findById(idNivEtu);        
        if(spe != null && optnivEtu.isPresent())
            return new ResponseEntity<List<Etudiant>>(
                eService.findBySpecialiteAndNiveauEtude( spe, optnivEtu.get()), HttpStatus.OK);
        else
            throw new UtilisateurServiceException("User not found");
    }

 /*   @PutMapping(path = "/{id}/")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant){
        etudiant.setId(id);
        return new ResponseEntity<Etudiant>(eService.upate(etudiant), HttpStatus.OK);

    }*/


    @PostMapping(path = "")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant){
        Etudiant etu = eService.save(etudiant);
        if(etu != null){
            return new ResponseEntity<Etudiant>(etu,HttpStatus.OK);    
        }else throw new UtilisateurServiceException("User update fail");    
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id){
        Etudiant etu = eService.findById(id);
        if(etu != null){
            eService.delete(etu);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
        }else throw new UtilisateurServiceException("User not saved");    
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestParam("file")MultipartFile file,

                                                   @RequestParam("etudiant") String etudiant) throws IOException{
        Etudiant etu = new ObjectMapper().readValue(etudiant, Etudiant.class);
        Etudiant updateStudent = eService.updateProfile(etu);
        if ( updateStudent != null)
         return new ResponseEntity<>(eService.updateProfile(etu), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(path = "/getProfilePhoto/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public  byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
       // Etudiant etu = eService.findById(id);
       // File file = new File(basePath+"/StudentProfile/"+etu.getPhotoName());
        //File file = new File(basePath+"/StudentProfile/"+"snpjL.png");
        return Files.readAllBytes(Paths.get(basePath+"/StudentProfile/"+"snpjL.png"));
    }
    
}