package io.github.llcfromhell;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.github.llcfromhell.worker.WatcherWorker;

public class Principal {

	static Path inputDir = Paths.get(System.getProperty("user.home") + "\\data\\in");
	
	public static void main(String[] args) {

		try {
				
			new Thread(new WatcherWorker(inputDir)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
