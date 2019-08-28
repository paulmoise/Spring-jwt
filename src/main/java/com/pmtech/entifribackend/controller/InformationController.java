package com.pmtech.entifribackend.controller;

import com.pmtech.entifribackend.dto.InformationDto;
import com.pmtech.entifribackend.entities.*;
import com.pmtech.entifribackend.exception.SpecialiteNotFoundException;
import com.pmtech.entifribackend.repository.*;
import com.pmtech.entifribackend.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class InformationController {
    @Autowired private InformationService informationService;

    @Autowired private CommuniqueRepository communiqueRepository;

    @Autowired private SpecialiteService specialiteService;

    @Autowired private NiveauEtudeRepository niveauEtudeRepository;







    @Value("${dir.basePath}")
    private String basePath;



    @PostMapping(path = "/informations")
    private ResponseEntity<Information> saveInformation(@RequestParam("file") MultipartFile file, @RequestParam("InfoDto") InformationDto dto ) throws IOException {
        Information information = informationService.save(dto, file);
        return new ResponseEntity<>(information,HttpStatus.CREATED);
    }


    @GetMapping("/communiques")
    public ResponseEntity<List<Communique>> getCommunique(){
        return new ResponseEntity<>(communiqueRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/communiquesBySpecialites")
    public ResponseEntity<List<Communique>> getCommuniqueBySpecialite(@RequestParam("codSpe") String codSpe){
        Specialite spe = specialiteService.findById(codSpe);
        if (spe!= null){
            return new ResponseEntity<>(communiqueRepository.findAllBySpecialites(spe), HttpStatus.OK);
        }else throw new SpecialiteNotFoundException("Specialite with id"+codSpe);

    }

    @GetMapping("/communiquesByNiveauEtudes")
    public ResponseEntity<List<Communique>> getCommuniqueByNiveauEtude(@RequestParam("idNivEtu") Long id){
        Optional<NiveauEtude> optionalNiveauEtude = niveauEtudeRepository.findById(id);

        if (optionalNiveauEtude.isPresent()){
            return new ResponseEntity<>(communiqueRepository.findAllByNiveauEtudes(optionalNiveauEtude.get()), HttpStatus.OK);
        }else throw new SpecialiteNotFoundException("Niveau Etude with id"+id);

    }

    @GetMapping("/communiquesBySpecialiteAndNiveauEtudes")
    public ResponseEntity<List<Communique>> getCommuniqueByNiveauEtudeAndSpecialite(@RequestParam("idNivEtu") Long id,
                                                                                    @RequestParam("codSpe") String codSpe){
        Optional<NiveauEtude> optionalNiveauEtude = niveauEtudeRepository.findById(id);
        Specialite spe = specialiteService.findById(codSpe);

        if (optionalNiveauEtude.isPresent() && spe != null){
            return new ResponseEntity<>(
                    communiqueRepository.findAllBySpecialitesAndNiveauEtudes(spe,optionalNiveauEtude.get()), HttpStatus.OK);
        }else throw new SpecialiteNotFoundException("Niveau Etude with id"+id);

    }



}
