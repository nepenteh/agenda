package com.ejerciciosmesa.agenda.appdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import org.springframework.stereotype.Component;


@Component
public class AppDataImpl  implements AppData {

	private String name; 
	private String author; 
	private int year; 
	private String web; 
	private String webURL; 
	private TreeMap<String,GeneralOption> generalOptions; 
	private ArrayList<GeneralOption> sortedGeneralOptions;
			
	public AppDataImpl() {
		name = "Agenda Telefónica";
		author = "José Manuel Rosado";
		year = 2023;
		web = "ejerciciosmesa.com";
		webURL = "https://ejerciciosmesa.com";
		
		generalOptions = new TreeMap<>();
		sortedGeneralOptions = new ArrayList<>();
		int order=0;
		
		GeneralOption opPersonas = new GeneralOption(order,"PERSONAS","Contactos","/personas/list","LIST");

opPersonas.addScreen("LIST","Persona (List)");
opPersonas.addScreen("CREATE","Persona (Create)");
opPersonas.addScreen("UPDATE","Persona (Update)");
opPersonas.addScreen("VIEW","Persona (View)");
opPersonas.addScreen("VIEWIMG","Persona - View Image");

opPersonas.setEmptyMessage("No data");

generalOptions.put("PERSONAS",opPersonas);
sortedGeneralOptions.add(opPersonas);

order++;

GeneralOption opEmpresas = new GeneralOption(order,"EMPRESAS","Empresas","/empresas/list","LIST");

opEmpresas.addScreen("LIST","Empresa (List)");
opEmpresas.addScreen("CREATE","Empresa (Create)");
opEmpresas.addScreen("UPDATE","Empresa (Update)");
opEmpresas.addScreen("VIEW","Empresa (View)");
opEmpresas.addScreen("VIEWIMG","Empresa - View Image");

opEmpresas.setEmptyMessage("No data");

generalOptions.put("EMPRESAS",opEmpresas);
sortedGeneralOptions.add(opEmpresas);

order++;


		
		Collections.sort(sortedGeneralOptions);
		
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	
	
	@Override
	public String getAuthor() {
		return author;
	}

	
	@Override
	public int getYear() {
		return year;
	}

	
	@Override
	public String getWeb() {
		return web;
	}
	
	
	@Override
	public String getWebUrl() {
		return webURL;
	}

	
	@Override
	public String getAuthorship() {
		String authorship = "";
		if(!author.isBlank() || !web.isBlank()) {
			authorship += author+" "+year;
			if(!web.isBlank()) authorship += " - "+web;
		}		
		return authorship.trim();
	}
	
	
	@Override
	public TreeMap<String, GeneralOption> getGeneralOptions() {
		return generalOptions;
	}
	
	@Override
	public ArrayList<GeneralOption> getSortedGeneralOptions() {
		return sortedGeneralOptions;
	}
	
	@Override
	public boolean isMainScreen(String optionCode, String screenCode) {
		return generalOptions.get(optionCode).getMainScreenCode().equals(screenCode);
	}
	
	@Override
	public String getMainScreenName(String optionCode) {
		return generalOptions.get(optionCode).getMainScreenName();
	}
	
	@Override
	public String getMainScreenLink(String optionCode) {
		return generalOptions.get(optionCode).getLink();
	}
	
	@Override
	public String getScreenName(String optionCode, String screenCode) {
		return generalOptions.get(optionCode).getScreen(screenCode);
	}

	@Override
	public String getEmptyMessage(String optionCode) {
		return generalOptions.get(optionCode).getEmptyMessage();
	}
		
}
