package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Convertitore {

	//	static final private String markInizioArray = "[";
	//	static final private String markFineArray = "]";
	//	static final private String markTraStringhe = ",";
	//	static final private String markDiStringa = " '' ";
	
	public List<String> creaListaDiStringhe (String stringa){
		List<String> listaDiStringhe = new LinkedList<>();
		stringa=stringa.substring(1, stringa.length()-1);
		for(String str:separaStringheSeparateDaVirgole(stringa)) {
			String nomeClasse = eliminaVirgolette(str);
			listaDiStringhe.add(nomeClasse);
		}

		return listaDiStringhe;
	}

	private String eliminaVirgolette (String stringaTraVirgolette) {
		return stringaTraVirgolette.substring(1,stringaTraVirgolette.length()-1);
	}

	private List<String> separaStringheSeparateDaVirgole(String stringheConVirgole) {
		return new LinkedList<String>(Arrays.asList(stringheConVirgole.split(",")));
	}
	
}
