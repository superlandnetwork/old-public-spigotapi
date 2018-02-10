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

package de.superlandnetwork.API;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	public File configFile = new File("plugins/API", "config.yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);

	public void setDefaults() {
		cfg.addDefault("ServerID", 0);

		cfg.addDefault("AllowFlyCommand", true);
		cfg.addDefault("AllowGameModeCommand", true);
		cfg.addDefault("AllowHealCommand", false);

		cfg.addDefault("MySQL.Host", "localhost");
		cfg.addDefault("MySQL.User", "User");
		cfg.addDefault("MySQL.Password", "Password");
		cfg.addDefault("MySQL.DB", "Datenbank");
		cfg.addDefault("MySQL.Port", "3306");

		cfg.options().copyDefaults(true);
		save();
	}

	public void save() {
		try {
			cfg.save(configFile);
		} catch (IOException e) {
			System.out.println("[API] Config Save Error");
			e.printStackTrace();
		}
	}

	public void load() {
		API.getInstance().ServerID = cfg.getInt("ServerID");
		
		API.getInstance().AllowFlyCommand = cfg.getBoolean("AllowFlyCommand");
		API.getInstance().AllowGamemodeCommand = cfg.getBoolean("AllowGameModeCommand");
		API.getInstance().AllowHealCommand = cfg.getBoolean("AllowHealCommand");

		API.getInstance().MySQL_Host = cfg.getString("MySQL.Host");
		API.getInstance().MySQL_Username = cfg.getString("MySQL.User");
		API.getInstance().MySQL_Password = cfg.getString("MySQL.Password");
		API.getInstance().MySQL_Database = cfg.getString("MySQL.DB");
		API.getInstance().MySQL_Port = cfg.getString("MySQL.Port");

	}

}
