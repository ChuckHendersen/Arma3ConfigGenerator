package main;

import java.util.LinkedList;
import java.util.List;

public class CostruttoreDiFileCpp {
	
	private static final String A3_Characters_F = "A3_Characters_F";
	
	private static final String templateCfgPatches1 = 
			"class CfgPatches {\n"
			+ "\tclass NoArmourVestForAI {\n"
			+ "\t\tunits[] = {};\n"
			+ "\t\tweapons[] = {";
			//Inserire lista delle nuove classi di giubbotti senza armatura
	private static final String templateCfgPatches2="};\n"
			+ "\t\trequiredVersion = 0.1;\n"
			+ "\t\trequiredAddons[] = {";  
			//Inserire lista di addon richiesti
	private static final String templateCfgPatches3="};\n"
				+ "\t};\n"
			+ "};\n";
	
	private static final String templateCfgWeaponsStart="class cfgWeapons {\n"
			+ "\tclass ItemInfo;";
	
	private static final String noArmourVestTemplate = "{\n"
			+ "		scope = 2;\n"
			+ "		weaponPoolAvailable=1;\n"
			+ "		author = \"Henderson\";\n"
			+ "		class ItemInfo : ItemInfo{\n"
			+ "			class HitpointsProtectionInfo {\n"
			+ "				class Neck {\n"
			//+ "					hitpointName = \"HitNeck\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.5;\n"
			+ "				};\n"
			+ "				\n"
			+ "				class Arms {\n"
			//+ "					hitpointName = \"HitArms\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.5;\n"
			+ "				};\n"
			+ "				\n"
			+ "				class Chest {\n"
			//+ "					hitpointName = \"HitChest\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.6;\n"
			+ "				};\n"
			+ "				\n"
			+ "				class Diaphragm {\n"
			//+ "					hitpointName = \"HitDiaphragm\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.6;\n"
			+ "				};\n"
			+ "				\n"
			+ "				class Abdomen {\n"
			//+ "					hitpointName = \"HitAbdomen\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.3;\n"
			+ "				};\n"
			+ "				\n"
			+ "				class Pelvis {\n"
			//+ "					hitpointName = \"HitPelvis\";\n"
			+ "					armor = 0;\n"
			//+ "					passThrough = 0.3;\n"
			+ "				};\n"
			+ "				\n"
//			+ "				class Body {\n"
//			+ "					hitpointName = \"HitBody\";\n"
//			+ "					passThrough = 0.8;\n"
//			+ "				};\n"
			+ "			};\n"
			+ "		};\n"
			+ "	};\n";
	
	
	
	private static final String templateCfgWeaponsEnd = "\n};";
	
	private static final String templateCfgMods = "class cfgMods {\n"
			+ "\tauthor = \"Henderson\";\n"
			+ "};";
	
	private List<String> listaClassiGiubbotti;
	private List<String> listaClassiGiubbottiNoArmour;
	private List<String> listaNomiAddon;
	
	public CostruttoreDiFileCpp (List<String> listaNomiClassi, List<String> listaNomiAddon) {
		this.listaClassiGiubbotti= new LinkedList<>(listaNomiClassi);
		this.listaClassiGiubbottiNoArmour = new LinkedList<>();
		this.listaNomiAddon = new LinkedList<>();
		
		for(String classe:listaClassiGiubbotti) {
			listaClassiGiubbottiNoArmour.add("NoArmour_"+classe);
		}
		this.listaNomiAddon.add(A3_Characters_F);
		this.listaNomiAddon.addAll(listaNomiAddon);
	}

	public String costruisciFile() {
		System.out.println(listaClassiGiubbotti);
		System.out.println(listaClassiGiubbottiNoArmour);
		System.out.println(listaNomiAddon);
		System.out.println("\n");
		String stringaFinale = costruisciCfgPatches()+"\n"+costruisciCfgWeapons()+"\n"+costruisciCfgMods();
		return stringaFinale;
	}
	
	private String costruisciCfgPatches() {
		StringBuilder costruttore = new StringBuilder();
		costruttore.append(templateCfgPatches1);
		for(String giubbottiNoArmour:listaClassiGiubbottiNoArmour) {
			costruttore.append(corniciatore(giubbottiNoArmour));
			costruttore.append(",");
		}
		costruttore.deleteCharAt(costruttore.length()-1);
		costruttore.append(templateCfgPatches2);
		for(String requiredAddon:listaNomiAddon) {
			costruttore.append(corniciatore(requiredAddon));
			costruttore.append(",");
		}
		costruttore.deleteCharAt(costruttore.length()-1);
		costruttore.append(templateCfgPatches3);
		return costruttore.toString();
	}
	
	private String costruisciCfgWeapons() {
		StringBuilder costr = new StringBuilder();
		costr.append(templateCfgWeaponsStart+"\n");
		for(int i=0;i<listaClassiGiubbotti.size();i++) {
			costr.append("\t");
			costr.append("class ");
			costr.append(listaClassiGiubbotti.get(i));
			costr.append(";\n");
			costr.append("\t");
			costr.append("class ");
			costr.append(listaClassiGiubbottiNoArmour.get(i)+" : "+listaClassiGiubbotti.get(i));
			costr.append("\n");
			costr.append("\t"+noArmourVestTemplate);
		}
		
		costr.append(templateCfgWeaponsEnd);
		return costr.toString();
	}
	
	private String costruisciCfgMods() {
		return templateCfgMods;
	}
	
	private String corniciatore(String stringa) {
		return "\""+stringa+"\"";
	}
	
	
}
