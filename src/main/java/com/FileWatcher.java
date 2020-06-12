package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcher {

	public static void main(String[] args) throws IOException, InterruptedException {

		Path textPath = Paths.get("\\\\BLR2-NIVINS1\\Users\\nivins1\\Desktop\\ISO8583Simulator");
		File file = new File("\\\\BLR2-NIVINS1\\Users\\nivins1\\Desktop\\ISO8583Simulator\\HPSConstants.properties");
		FileInputStream FIS;
		InputStreamReader ISR;
		BufferedReader br;

		WatchService watchService = FileSystems.getDefault().newWatchService();
		textPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

		boolean valid;
		String temp, content = "";
		do {
			valid = true;
			FIS = new FileInputStream(file);
			ISR = new InputStreamReader(FIS);
			br = new BufferedReader(ISR);
			WatchKey watchKey = watchService.take();
			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
					while((temp = br.readLine())!=null) {
						content = content+temp+"\n";
					}
					System.out.println(content);
					temp = "";
					content = "";
					br.close();
				}
			}
			watchKey.reset();
		} while (true);

	}
}
