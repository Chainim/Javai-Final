package com.unicamp.mc322.javai_final.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Localizer {
	private static Localizer instance = null;
	
	private Lang lang;
	private final String langFolder = "./lang";
	private File langFile;
	private Map<String, String> resolvedNames;
	
	private Localizer(Lang lang) {
		this.lang = lang;
		this.langFile = new File(langFolder + "/" + this.lang.toString() + ".lang");
		this.resolvedNames = new HashMap<String, String>();
	}
	
	private void loadFromFile() {
		Scanner s;
		try {
			s = new Scanner(langFile);
			while(s.hasNext("[\\w\\.]+")) {
				String key = s.next("[\\w\\.]+");
				s.next("=");
				String val = s.next("\"[\\w\\.]+\"");
				val = val.substring(1, val.length() - 1);
				resolvedNames.put(key, val);
			}
				
			s.close();
		} catch (FileNotFoundException e) {
			System.err.println("Lang file not found!");
			e.printStackTrace();
		}
	}
	
	public Lang getLang() {
		return lang;
	}
	
	public String getLocalization(String name) {
		if(resolvedNames.containsKey(name))
			return resolvedNames.get(name);
		System.err.println("Localization not found for \"" + name + "\"!");
		return name;
	}
	
	public static void localizerInit(Lang lang) {
		instance = new Localizer(lang);
		instance.loadFromFile();
	}
	
	public static Localizer getInstance() {
		if(instance == null)
			System.err.println("Localizer not initialized");
		return instance;
	}
}
