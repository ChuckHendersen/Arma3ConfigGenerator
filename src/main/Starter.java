package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Starter {

	public static void main(String[] args) throws IOException {
		Convertitore c = new Convertitore();
		FileReader reader = new FileReader();
		String stringaSporcaGiubotti = reader.readFile("vestList.txt", StandardCharsets.UTF_8);
		String stringaSporcaAddon = reader.readFile("addonsList.txt", StandardCharsets.UTF_8);
		CostruttoreDiFileCpp cdfc= new CostruttoreDiFileCpp(c.creaListaDiStringhe(stringaSporcaGiubotti),c.creaListaDiStringhe(stringaSporcaAddon));
		Salvatore s = new Salvatore(cdfc.costruisciFile(),".cpp");
		s.creaFileESalvaStringa();
		System.out.println("*******************************");
		System.out.println("config.cpp succesfully created.");
	}

}
