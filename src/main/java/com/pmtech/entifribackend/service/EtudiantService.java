package com.pmtech.entifribackend.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pmtech.entifribackend.entities.Etudiant;
import com.pmtech.entifribackend.entities.NiveauEtude;
import com.pmtech.entifribackend.entities.Specialite;
import com.pmtech.entifribackend.repository.EtudiantRepository;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * EtudiantService
 */
@Service
public class EtudiantService {

    @Autowired private EtudiantRepository etudiantRepository;
    private  UploadPathService uploadPathService;

    public Etudiant save(Etudiant etu){
       return etudiantRepository.save(etu);
    }

    public List<Etudiant> findAll(){
        return etudiantRepository.findAll();
     }

    public Etudiant update(Etudiant etu){
        return etudiantRepository.save(etu);
     }

    public void delete(Etudiant etu){
         etudiantRepository.delete(etu);
     }

    public Etudiant findById(Long id){
         Optional<Etudiant> optEtu = etudiantRepository.findById(id);
         if(optEtu.isPresent()) return optEtu.get();
         else return null;

     }


    public List<Etudiant> findBySpecialite(Specialite spe){
        return etudiantRepository.findBySpecialite(spe);
    } 

    public List<Etudiant> findByNiveauEtude(NiveauEtude nEtude){
        return etudiantRepository.findByNiveauEtude(nEtude);
    } 

    public List<Etudiant> findBySpecialiteAndNiveauEtude(Specialite spe,NiveauEtude nEtude){
        return etudiantRepository.findBySpecialiteAndNiveauEtude(spe,nEtude);
    } 

    public Etudiant findByNumMtle(Long numMtle){
        return etudiantRepository.findByNumMtle(numMtle);
    }


    public Etudiant updateProfile(Etudiant etudiant) {
        etudiant.setUpdateDate(new Date());
        MultipartFile file = etudiant.getFile();
        if( file != null){
            String fileName = file.getOriginalFilename();
            String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+
                    FilenameUtils.getExtension(fileName);
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "StudentProfile");
            if(storeFile != null){
                try {
                    FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            etudiant.setPhotoName(modifiedFileName);
            return etudiantRepository.save(etudiant);
        }else{
            return null;
        }
    }
}