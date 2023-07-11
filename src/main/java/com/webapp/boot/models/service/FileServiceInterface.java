package com.webapp.boot.models.service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceInterface {

	public Resource load (String filename);
	
	public String copy (MultipartFile file) throws IOException;
	
	public boolean delete (String filename);
	
	public void deleteAll();
	
	public void init() throws IOException;
	
}
