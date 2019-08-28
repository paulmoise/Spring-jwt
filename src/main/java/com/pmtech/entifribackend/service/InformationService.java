package com.pmtech.entifribackend.service;

import com.pmtech.entifribackend.dto.InformationDto;
import com.pmtech.entifribackend.entities.*;
import com.pmtech.entifribackend.repository.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;


@Service
public class InformationService {
    @Autowired private NoteDInformationRepository noteDInformationRepository;
    @Autowired private ResultExamRepository resultExamRepository;
    @Autowired private EmploiDuTempsRepository emploiDuTempsRepository;
    @Autowired private CommuniqueRepository communiqueRepository;
    @Autowired private SpecialiteService specialiteService;
    @Autowired private NiveauEtudeRepository niveauEtudeRepository;
    @Autowired private EnseignantService enseignantService;
    @Autowired private EtudiantService etudiantService;
    @Autowired private EvenementRepository evenementRepository;
    @Autowired private UploadPathService uploadPathService;

    public Information save(InformationDto dto, MultipartFile file){
        Information information = new Information();
        if(dto.getTypInfo().equals("CM")){
            information = saveCommunique(dto);
            setDestinationForInfomation(dto, information);
        }else if(dto.getTypInfo().equals("NI")){
            information = saveNoteDInformation(dto, file);
        }else if(dto.getTypInfo().equals("RE")){
            information = saveResultExam(dto, file);
            setDestinationForInfomation(dto, information);
        }else if(dto.getTypInfo().equals("EMP")){
            information = saveEmploiDuTemps(dto, file);
            setDestinationForInfomation(dto, information);
        }else if(dto.getTypInfo().equals("EVENT")){
            information = saveEvent(dto, file);
            setDestinationForInfomation(dto, information);
        }
        return information;
    }


    public Information saveCommunique(InformationDto dto){
        Communique communique = new Communique();
        communique.setDatPost(new Date());
        communique.setTitle(dto.getTitle());
        communique.setDestinataire(dto.getDestinaire());
        communique.setObject(dto.getObject());
        return communiqueRepository.save(communique);
    }
    public Information saveEvent(InformationDto dto, MultipartFile file){
        Evenement evenement = new Evenement();
        evenement.setDatPost(new Date());
        evenement.setDatEven(dto.getDatEven());
        evenement.setObject(dto.getObject());
        evenement.setTitle(dto.getTitle());
        evenement.setDestinataire(dto.getDestinaire());
        evenement.setObject(dto.getObject());
        String modifiedFileName = saveFileInServeur(file, "EventImages");
        if( modifiedFileName!=null)
            evenement.setImage(modifiedFileName);

        return evenementRepository.save(evenement);
    }
    public Information saveNoteDInformation(InformationDto dto, MultipartFile file) {
        NoteDInformation obj = new NoteDInformation();
        obj.setDatPost(new Date());
        obj.setObject(dto.getObject());
        obj.setRef(dto.getRef());
        obj.setTitle(dto.getTitle());
        obj.setDestinataire(dto.getDestinaire());
        //MultipartFile file = dto.getFile();
        String modifiedFileName = saveFileInServeur(file, "NoteDInformation");
        if( modifiedFileName!=null)
            obj.setFileName(modifiedFileName);

        return noteDInformationRepository.save(obj);
    }
    public Information saveResultExam(InformationDto dto,MultipartFile file) {
        ResultExam obj = new ResultExam();
        obj.setDatPost(new Date());
        obj.setObject(dto.getObject());
        obj.setDescription(dto.getDescription());
        obj.setTitle(dto.getTitle());
        obj.setDestinataire(dto.getDestinaire());
        //MultipartFile file = dto.getFile();
        String modifiedFileName = saveFileInServeur(file, "ResultExam");
        if( modifiedFileName!=null)
            obj.setFileName(modifiedFileName);

        return resultExamRepository.save(obj);
    }
    public Information saveEmploiDuTemps(InformationDto dto, MultipartFile file) {
        EmploiDuTemps obj = new EmploiDuTemps();
        obj.setDatPost(new Date());
        obj.setObject(dto.getObject());
        obj.setPeriode(dto.getPeriode());
        obj.setTitle(dto.getTitle());
        obj.setDestinataire(dto.getDestinaire());
        //MultipartFile file = dto.getFile();
        String modifiedFileName = saveFileInServeur(file, "EmploiDuTemps");
        if( modifiedFileName!=null)
            obj.setFileName(modifiedFileName);

        return emploiDuTempsRepository.save(obj);
    }

    //save information's file in server
    public String saveFileInServeur(MultipartFile file, String path) {

        if (file != null) {
            String fileName = file.getOriginalFilename();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." +
                    FilenameUtils.getExtension(fileName);
            File storeFile = uploadPathService.getFilePath(modifiedFileName, path);
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return modifiedFileName;
        }else return null;
    }

    // Define the information's recevers
    public void setDestinationForInfomation(InformationDto dto, Information information){

        if(dto.getSpecialites() != null && dto.getSpecialites().length > 0 ){
            System.err.println("Here");
            for (String codSpe: dto.getSpecialites()){
                Specialite specialite = specialiteService.findById(codSpe);
                specialite.setInformation(information);
                specialiteService.save(specialite);
            }
        }else if(dto.getNiveauEtudes() != null && dto.getNiveauEtudes().length > 0 ){
            for (Long idNivEtu : dto.getNiveauEtudes()){
                NiveauEtude niveauEtude = niveauEtudeRepository.findById(idNivEtu).get();
                niveauEtude.setInformation(information);
                niveauEtudeRepository.save(niveauEtude);
            }
        }else if (dto.getEnseignants() != null && dto.getEnseignants().length > 0){
            for (Long id : dto.getEnseignants()){
                Enseignant enseignant = enseignantService.findById(id);
                enseignant.setInformation(information);
                enseignantService.update(enseignant);
            }
        }else if(dto.getEtudiants() != null && dto.getEtudiants().length > 0){
            for (Long id : dto.getEtudiants()){
                Etudiant etudiant = etudiantService.findById(id);
                etudiant.setInformation(information);
                etudiantService.update(etudiant);
            }
        }
    }
}
