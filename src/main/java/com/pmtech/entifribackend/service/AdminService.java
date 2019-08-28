package com.pmtech.entifribackend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.pmtech.entifribackend.entities.*;
import com.pmtech.entifribackend.repository.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;




/**
 * AdminService
 */
@Service
public class AdminService {

	@Autowired private AdminRepository adminRepository;
	@Autowired private EtudiantRepository etudiantRepository;
	@Autowired private EnseignantRepository enseignantRepository;
	@Autowired private MatiereRepository matiereRepository;
	@Autowired  private AppRoleRepository apppRoleRepository;


	public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Admin save(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin update(Admin admin){
        return adminRepository.save(admin);
    }

    public void delete(Admin admin){
        adminRepository.delete(admin);
    }

    

    // read data from file

    public boolean saveDataFromfile(MultipartFile file, Specialite spe, NiveauEtude niveauEtude) throws ParseException {
		boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        
		/*if (extension.equalsIgnoreCase("csv")){
			isFlag = readDataFromCsv(file);
        }else */
        
        if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")){

			isFlag = readStudentsDataFromExcel(file, spe, niveauEtude);

		}
		return isFlag;
    }

	public boolean saveTeacherDataFromfile(MultipartFile file) throws ParseException {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")){
			isFlag = readTeachersDataFromExcel(file);
		}
		return isFlag;
	}


	public boolean saveMatiereDataFromfile(MultipartFile file, Specialite spe, NiveauEtude niveauEtude) throws ParseException {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")){
			isFlag = readMatieresDataFromExcel(file, spe, niveauEtude);
		}
		return isFlag;
	}




	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
				 if (extension.equalsIgnoreCase("xlsx")) {
					workbook = new  XSSFWorkbook(file.getInputStream());

				 }else if (extension.equalsIgnoreCase("xls")  ){
				 	System.err.println("Extension is "+ extension);
				 	workbook = new HSSFWorkbook(file.getInputStream());
				 }
		}catch (Exception e){
			e.printStackTrace();
		}
		return workbook;
	}  
    

    private boolean readStudentsDataFromExcel(MultipartFile file, Specialite spe, NiveauEtude nivEtude) throws ParseException {

		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(1);
		final String OLD_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);

		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()){
			Row row = rows.next();
			Etudiant etudiant = new Etudiant();
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC){
				long matricule =  (long)(row.getCell(0).getNumericCellValue());
				etudiant.setNumMtle(new Long(matricule));
				etudiant.setUsername(NumberToTextConverter.toText(row.getCell(0).getNumericCellValue()));
			}
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING){
				etudiant.setNom(row.getCell(1).getStringCellValue());
			}
			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING){
				etudiant.setPrenom(row.getCell(2).getStringCellValue());
			}
			if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING){
				etudiant.setSexe( (row.getCell(3).getStringCellValue()).charAt(0));
			}
			if (row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING){
			Date date = sdf2.parse(row.getCell(4).getStringCellValue());
				etudiant.setDateNaissance(date);
			}
			if (row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING){
				etudiant.setLieuNaissance(row.getCell(5).getStringCellValue());
			}
			if (row.getCell(6).getCellType() == Cell.CELL_TYPE_NUMERIC){
				etudiant.setCIN(NumberToTextConverter.toText(row.getCell(6).getNumericCellValue()));
			}
			
			if (row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING){
				etudiant.setEmail(row.getCell(7).getStringCellValue());;
			}
			if (row.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC){
				etudiant.setPhoneNumber(NumberToTextConverter.toText(row.getCell(8).getNumericCellValue()) );
			}
			AppRole role = apppRoleRepository.findByRoleName("STUDENT");

			etudiant.getRoles().add(role);
			etudiant.setSpecialite(spe);
			etudiant.setNiveauEtude(nivEtude);
			etudiantRepository.save(etudiant);
		}
		return true;
	}


	private boolean readTeachersDataFromExcel(MultipartFile file) throws ParseException {

		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(1);
		final String OLD_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT);

		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()){
			Row row = rows.next();
			Enseignant enseignant = new Enseignant();
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC){
				long matricule =  (long)(row.getCell(0).getNumericCellValue());
				enseignant.setNumMtle(new Long(matricule));
				enseignant.setUsername(NumberToTextConverter.toText(row.getCell(0).getNumericCellValue()));
			}
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setNom(row.getCell(1).getStringCellValue());
			}
			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setPrenom(row.getCell(2).getStringCellValue());
			}
			if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setSexe( (row.getCell(3).getStringCellValue()).charAt(0));
			}
			if (row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING){
				Date date = sdf2.parse(row.getCell(4).getStringCellValue());
				enseignant.setDateNaissance(date);
			}
			if (row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setLieuNaissance(row.getCell(5).getStringCellValue());
			}
			if (row.getCell(6).getCellType() == Cell.CELL_TYPE_NUMERIC){
				enseignant.setCIN(NumberToTextConverter.toText(row.getCell(6).getNumericCellValue()));
			}

			if (row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setEmail(row.getCell(7).getStringCellValue());;
			}
			if (row.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC){
				enseignant.setPhoneNumber(NumberToTextConverter.toText(row.getCell(8).getNumericCellValue()) );
			}
			if (row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING){
				enseignant.setGrade(row.getCell(9).getStringCellValue());;
			}

			//AppRole role = apppRoleRepository.save(new AppRole(ROLE.TEACHER));
			AppRole role = apppRoleRepository.findByRoleName("TEACHER");
			enseignant.getRoles().add(role);
			enseignantRepository.save(enseignant);
		}
		return true;
	}


	private boolean readMatieresDataFromExcel(MultipartFile file, Specialite spe, NiveauEtude nivEtude){

		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()){
			Row row = rows.next();
			Matiere matiere = new Matiere();
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING){
				matiere.setCodMat(row.getCell(0).getStringCellValue());
			}
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING){
				matiere.setLibMat(row.getCell(1).getStringCellValue());
			}
			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){

				matiere.setCredit(row.getCell(2).getNumericCellValue());
			}

			matiere.setSpecialite(spe);
			matiere.setNiveauEtude(nivEtude);
			matiereRepository.save(matiere);
		}
		return true;
	}
}