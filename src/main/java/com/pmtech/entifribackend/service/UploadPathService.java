package com.pmtech.entifribackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;

@Service
@Transactional
public class UploadPathService {

	@Autowired ServletContext context;
	
    @Value("${dir.basePath}")
    private  String basePath;
	
	public File getFilePath(String modifiedFileName, String path) {
		boolean exists = new File(basePath+"/"+path+"/").exists();
		if(!exists) {
			new File(basePath+"/"+path+"/").mkdir();
		}
		String modifiedFilePath = basePath+"/"+path+"/"+File.separator+modifiedFileName;
		File file = new File(modifiedFilePath);
		return file;
	}
}
