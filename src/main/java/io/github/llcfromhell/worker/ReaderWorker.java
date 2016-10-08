package io.github.llcfromhell.worker;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.llcfromhell.service.DataAnalyzer;
import io.github.llcfromhell.service.DataReader;
import io.github.llcfromhell.service.FileWriter;

public class ReaderWorker implements Runnable {

	private DataReader dataReader = new DataReader();
	private DataAnalyzer dataAnalyzer = new DataAnalyzer();
	private FileWriter fileWriter = new FileWriter();

	private Path pathToRead;

	public ReaderWorker(Path pathToRead) {
		super();
		this.pathToRead = pathToRead;
	}

	@Override
	public void run() {

		try (DirectoryStream<Path> files = Files.newDirectoryStream(pathToRead, "*.{dat}")) {

			files.forEach(file -> dataReader.readDataFrom(file));

			dataReader.getListData().forEach(data -> dataAnalyzer.analyze(data));

			dataAnalyzer.getListAnalyzedData().forEach(analyzedData -> fileWriter.writeToFileThe(analyzedData));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
