package com.pmtech.entifribackend.controller;

import java.text.ParseException;
import java.util.Optional;

import com.pmtech.entifribackend.dto.EnseignantMatiereDto;
import com.pmtech.entifribackend.dto.SpecialiteNiveauEtudeDto;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.exception.SpecialiteNotFoundException;
import com.pmtech.entifribackend.repository.NiveauEtudeRepository;
import com.pmtech.entifribackend.service.AdminService;
import com.pmtech.entifribackend.service.SpecialiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * AdminController
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private SpecialiteService specialiteService;
    @Autowired private NiveauEtudeRepository nRepository;

    
    @PostMapping("/uploadStudent")
    public ResponseEntity<Void> saveStudentFromFile(@RequestParam("file") MultipartFile file) throws ParseException{

      // Specialite spe = specialiteService.findById(dto.getCodSpe());
        Specialite spe = specialiteService.findById("GL");
        Optional<NiveauEtude> opNiveauEtude = nRepository.findById(1l);

        System.err.println("Specialite = "+spe+" ******niveau"+opNiveauEtude.get());
        if( opNiveauEtude.isPresent() && spe != null ){
            adminService.saveDataFromfile(file, spe, opNiveauEtude.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else throw new SpecialiteNotFoundException("GL");
    //    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/uploadTeacher")
    public ResponseEntity<Void> saveTeacherFromFile(@RequestParam("file") MultipartFile file) throws ParseException{
        if( !file.isEmpty() ){
            adminService.saveTeacherDataFromfile(file);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/uploadMatiere")
    public ResponseEntity<Void> saveMatiereFromFile(@RequestParam("file") MultipartFile file) throws ParseException{

        Specialite spe = specialiteService.findById("GL");
        Long id = 2L;
        Optional<NiveauEtude> opNiveauEtude = nRepository.findById(id);
        System.err.println("Specialite = "+spe+" ******niveau"+opNiveauEtude.get());
        if( opNiveauEtude.isPresent() && spe != null ){
            adminService.saveMatiereDataFromfile(file, spe, opNiveauEtude.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else throw new SpecialiteNotFoundException("GL");
        //    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}