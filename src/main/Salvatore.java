package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Salvatore {
	private String stringaDaSalvare;
	
	public Salvatore (String stringa,String ext) {
		this.stringaDaSalvare=stringa;
	}
	
	public void creaFileESalvaStringa() throws IOException {
		Path percorso = Paths.get("config.cpp");
		byte[] arr = this.stringaDaSalvare.getBytes();
			Files.write(percorso,arr);
	}
	
}
