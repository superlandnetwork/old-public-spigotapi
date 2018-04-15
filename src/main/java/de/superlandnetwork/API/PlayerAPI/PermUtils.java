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

package de.superlandnetwork.API.PlayerAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermUtils {

	public static final String Version = "1.1";

	/**
	 * @param filename
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getStringListFromFile(String filename) {
		File f = new File("plugins/API/", filename + ".permissons");
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
		File f = new File("plugins/API/", filename + ".permissons");
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
			bw.write("#Permissons File version: " + Version);
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
	 * @return HashMap<Integer, List<Integer>>
	 */
	public HashMap<Integer, List<Integer>> LoadOrder(ArrayList<String> list) {
		HashMap<Integer, List<Integer>> List = new HashMap<>();
		for (String str : list) {
			String[] s = str.split(":");
			List<Integer> l = new ArrayList<>();
			for (int i = 1; i < s.length; i++) {
				l.add(Integer.parseInt(s[i]));
			}
			List.put(Integer.parseInt(s[0]), l);
		}
		return List;
	}

	public void DefaultOrder() {
		ArrayList<String> list = new ArrayList<>();
		list.add("1:1");// Spieler
		list.add("2:1:2");// Premium
		list.add("3:1:2:3");// Premium+
		list.add("4:1:2:3:4");// YouTuber
		list.add("5:1:2:3:4:5");// Supporter
		list.add("6:1:2:3:4:5");// Supporterin
		list.add("7:1:2:3:4:5:7");// Moderator
		list.add("8:1:2:3:4:5:7");// Moderatorin
		list.add("9:1:2:3:4:5:7:9");// SrModerator
		list.add("10:1:2:3:4:5:7:9");// SrModeratorin
		list.add("11:1:2:3:4:5:11");// Builder
		list.add("12:1:2:3:4:5:11");// Builderin
		list.add("13:1:2:3:4:5:13");// Helfer
		list.add("14:1:2:3:4:5:14");// Helferin
		list.add("15:1:2:3:4:5:7:9:15");// Developer
		list.add("16:1:2:3:4:5:7:9:15");// Developerin
		list.add("17:1:2:3:4:5:7:9:11:15:17");// Administrator
		list.add("18:1:2:3:4:5:7:9:11:15:17");// Administratorin
		list.add("19:1:2:3:4:5:7:9:11:15:17:19");// Owner
		list.add("20:1:2:3:4:5:7:9:11:15:17:19");// Ownerin
		save("order", list);
	}

}
