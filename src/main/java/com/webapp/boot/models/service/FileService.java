package com.webapp.boot.models.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements FileServiceInterface {

	private final static String UPLOADS_FOLDER = "uploads";
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Resource load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		/*Guardando imagenes en directorio externo configurado en MvcConfig
		String rootPath = "C://Temp//uploads";
			byte[] bytes = foto.getBytes();
			Path ruta = Paths.get(rootPath + "//"+ foto.getOriginalFilename());
			Files.write(ruta, bytes);*/
		
		
		//Agregando directorio absoluto y externo en la raíz del proyecto
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		log.info("rootPath"+ rootPath);
	
		Files.copy(file.getInputStream(), rootPath);
			
		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		
		if(archivo.exists() && archivo.canRead()) {
			if(archivo.delete()) {
				return true;
			}
		}
		return false;
	}
	
	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();

	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		// Se inicializa a traves del run implementado de CommandLineRunner 
		// Dentro del paquete principal de SpringBoot
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		
	}

}
