package io.github.llcfromhell.worker;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatcherWorker implements Runnable {

	private Path pathToWatch;

	public WatcherWorker(Path pathToWatch) {
		this.pathToWatch = pathToWatch;
	}


	@Override
	public void run() {
		
		while (true) {

			try {
				Files.createDirectory(pathToWatch);
			} catch (FileAlreadyExistsException e1) {

			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			try {
				
				WatchService service = FileSystems.getDefault().newWatchService();

				pathToWatch.register(service, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

				WatchKey key = service.take();

				List<WatchEvent<?>> events = key.pollEvents();

				events.forEach(event -> check(event, key));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	public void check(WatchEvent<?> event, WatchKey key) {

		if (OVERFLOW.equals(event.kind())) {
			return;
		}

		Path dir = (Path)key.watchable();
		Path pathToRead = dir.resolve((Path) event.context());
		
		//Path pathToRead = dir.resolve(event.context()); pathEvent.context();
		
		new Thread(new ReaderWorker(pathToRead.getParent())).start();
		
	}

}
