//  ___          _                _       _     ___   ___ 
// / __|  _ __  (_)  __ _   ___  | |_    /_\   | _ \ |_ _|
// \__ \ | '_ \ | | / _` | / _ \ |  _|  / _ \  |  _/  | | 
// |___/ | .__/ |_| \__, | \___/  \__| /_/ \_\ |_|   |___|
//       |_|        |___/                                 
//
// Copyright (C) 2017 - 2018 Filli IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.API.WorldAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldUtils {

	public static final String Version = "1.0";

	/**
	 * @param filename
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getStringListFromFile(String filename) {
		File f = new File("plugins/API/", filename + ".list");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<>();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("#")) {
					list.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param filename
	 * @param list
	 * @return true or false
	 */
	public boolean save(String filename, ArrayList<String> list) {
		File f = new File("plugins/API/", filename + ".list");
		String newLine = System.getProperty("line.separator");
		if (f.exists()) {
			return false;
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("#World File version: " + Version);
			bw.write(newLine);
			for (String s : list) {
				bw.write(s);
				bw.write(newLine);
			}
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param list
	 * @return HashMap<String, Integer>
	 */
	public HashMap<String, Integer> LoadWorld(ArrayList<String> list) {
		HashMap<String, Integer> List = new HashMap<>();
		for (String str : list) {
			String[] s = str.split(":");
			List.put(s[0], Integer.parseInt(s[1]));
		}
		return List;
	}

	public void DefaultWorld() {
		ArrayList<String> list = new ArrayList<>();
		// Aufbau Save %Name%:%WorldTypeID%
		list.add("world:0");// world (Normal)
		save("world", list);
	}

}
