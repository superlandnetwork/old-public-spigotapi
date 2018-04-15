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

package de.superlandnetwork.API;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.superlandnetwork.API.PlayerAPI.PermUtils;
import de.superlandnetwork.API.WorldAPI.WorldUtils;

public class API extends JavaPlugin {

	/* Instancen */
	public static API instance;
	public Config Config;
	public MySQL MySQL;

	/* Booleans */
	public boolean AllowGamemodeCommand;
	public boolean AllowFlyCommand;
	public boolean AllowHealCommand;

	/* MySQL */
	public String MySQL_Host;
	public String MySQL_Username;
	public String MySQL_Password;
	public String MySQL_Database;
	public String MySQL_Port;

	/* Ints */
	public int ServerID;
	
	public List<String> UsedNicks = new ArrayList<>();
	public List<String> AllNicksDB = new ArrayList<>();

	/*
	 * -------------------------------------------------------------------------
	 */

	public void onEnable() {
		/* Instances */
		instance = this;
		Config = new Config();
		MySQL = new MySQL();

		Config.setDefaults();
		Config.load();
		System.out.println("[API] Enabled!");
		MySQL.connect();
		System.out.println("[API] Version: " + getDescription().getVersion());
		new PermUtils().DefaultOrder();
		new WorldUtils().DefaultWorld();
		UpdateNicksDB();
	}

	public void onDisable() {
		System.out.println("[API] Disabled!");
		MySQL.close();
	}

	/*
	 * -------------------------------------------------------------------------
	 */

	/**
	 * @return instance
	 */
	public static API getInstance() {
		return instance;
	}

	/**
	 * @return MySQL
	 */
	public MySQL getMySQL() {
		return MySQL;
	}

	/**
	 * @param string
	 */
	public void sendMessageToAllPlayers(String string) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(string);
		}
	}

	/**
	 * @param string
	 * @param Permisson
	 */
	public void sendMessageToAllPlayers(String string, String Permisson) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.hasPermission(Permisson))
				all.sendMessage(string);
		}
	}

	/**
	 * @param string
	 */
	public void sendMessageToTeam(String string) {
		sendMessageToAllPlayers(string, "ccl.TeamChat");
	}

	public void UpdateNicksDB() {
		AllNicksDB.clear();
		ResultSet rs = API.getInstance().getMySQL().getResult("SELECT * FROM `Nick`");
		try {
			while (rs.next()) {
				if (rs.getInt("OK") == 1) {
					AllNicksDB.add(rs.getString("NickName"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public Integer randomInt(int min, int max) {
		Random r = new Random();
		int i = r.nextInt((max - min) + 1) + min;
		return i;
	}

	/*
	 * -------------------------------------------------------------------------
	 */
}
