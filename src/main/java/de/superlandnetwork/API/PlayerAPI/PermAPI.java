//  ___          _                _       _     ___   ___ 
// / __|  _ __  (_)  __ _   ___  | |_    /_\   | _ \ |_ _|
// \__ \ | '_ \ | | / _` | / _ \ |  _|  / _ \  |  _/  | | 
// |___/ | .__/ |_| \__, | \___/  \__| /_/ \_\ |_|   |___|
//       |_|        |___/                                 
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.API.PlayerAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.superlandnetwork.API.API;

public class PermAPI {

	/**
	 * @param GroupID
	 * @return List<String>
	 */
	public static List<String> getGroupPermisons(int GroupID) {
		List<String> list = new ArrayList<String>();
		ResultSet rs = API.getInstance().getMySQL().getResult("SELECT * FROM `Permisson_Permissons`");
		try {
			while (rs.next()) {
				if (rs.getInt("GroupID") == GroupID) {
					list.add(rs.getString("Permissons"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param GroupID
	 * @return List<String>
	 */
	public static List<String> getGroupPermisonsOrdet(int GroupID) {
		List<String> list = new ArrayList<String>();
		PermUtils pu = new PermUtils();
		ArrayList<String> l = pu.getStringListFromFile("order");
		HashMap<Integer, List<Integer>> List = pu.LoadOrder(l);
		if (List.containsKey(GroupID)) {
			List<Integer> l2 = new ArrayList<Integer>();
			l2 = List.get(GroupID);
			for (int i = 0; i < l2.size(); i++) {
				list.addAll(getGroupPermisons(l2.get(i)));
			}
		}
		return list;
	}

}
