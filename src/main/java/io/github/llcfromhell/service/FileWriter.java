package io.github.llcfromhell.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import io.github.llcfromhell.domain.AnalyzedData;

public class FileWriter {

	private static final Logger LOGGER = Logger.getLogger(FileWriter.class.getName()); 
	
	static String outdir = System.getProperty("user.home") + "/data/out/";

	public void writeToFileThe(AnalyzedData analyzedData) {
		
		Path path = Paths.get(outdir + analyzedData.getFlatFileNameWithoutExtension() + ".done.dat" );
		
		try {
			Files.createDirectory(Paths.get(outdir));
		} catch (FileAlreadyExistsException e1) {

		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			
			LOGGER.info("Iniciando escrita do arquivo: " + path.toString());
			
		    writer.write(analyzedData.toString());
		    
		    LOGGER.info("Arquivo : " + path.toString() + " - escrita feita com sucesso.");
		    
		} catch (Exception e) {
			
			LOGGER.severe("Falha na escrita do arquivo : " + path.toString());
			e.printStackTrace();
			
		}
		
	}
	
	
}
