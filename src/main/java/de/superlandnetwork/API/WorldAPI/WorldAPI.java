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

package de.superlandnetwork.API.WorldAPI;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

public class WorldAPI {

	private String Name;

	/**
	 * @param name
	 */
	public WorldAPI(String name) {
		this.Name = name;
	}

	public void createCleanWorld() {
		WorldCreator c = new WorldCreator(this.Name);
		c.generator(new CleanWorld());
		c.createWorld();
	}

	public void createNormalWorld() {
		WorldCreator c = new WorldCreator(this.Name);
		c.environment(Environment.NORMAL);
		c.type(WorldType.NORMAL);
		c.createWorld();
	}

	public void createNetherWorld() {
		WorldCreator c = new WorldCreator(this.Name);
		c.environment(Environment.NETHER);
		c.type(WorldType.CUSTOMIZED);
		c.createWorld();
	}

	public void createEndWorld() {
		WorldCreator c = new WorldCreator(this.Name);
		c.environment(Environment.THE_END);
		c.type(WorldType.CUSTOMIZED);
		c.createWorld();
	}

	public void createFlatWorld() {
		WorldCreator c = new WorldCreator(this.Name);
		c.environment(Environment.NORMAL);
		c.type(WorldType.FLAT);
		c.createWorld();
	}

	/**
	 * @param name
	 * @param env
	 * @param type
	 */
	public static void createSpecialWorld(String name, Environment env, WorldType type) {
		WorldCreator c = new WorldCreator(name);
		c.environment(env);
		c.type(type);
		c.createWorld();
	}

	/**
	 * @param name
	 * @param env
	 * @param type
	 * @param generator
	 */
	public static void createSpecialWorld(String name, Environment env, WorldType type, ChunkGenerator generator) {
		WorldCreator c = new WorldCreator(name);
		c.environment(env);
		c.type(type);
		c.generator(generator);
		c.createWorld();
	}

	public void LoadWorld() {
		if (Bukkit.getWorld(this.Name) == null) {
			WorldUtils wu = new WorldUtils();
			if (wu.LoadWorld(wu.getStringListFromFile("world")).containsKey(this.Name)) {
				Generat();
			}
		}
		return;
	}

	private void Generat() {
		WorldUtils wu = new WorldUtils();
		HashMap<String, Integer> Map = wu.LoadWorld(wu.getStringListFromFile("world"));
		if (Map.get(this.Name) == 0) { // Normal World
			createNormalWorld();
		} else if (Map.get(this.Name) == 1) { // Nether World
			createNetherWorld();
		} else if (Map.get(this.Name) == 2) { // End World
			createEndWorld();
		} else if (Map.get(this.Name) == 3) { // Flat World
			createFlatWorld();
		} else if (Map.get(this.Name) == 4) { // Clean World
			createCleanWorld(); 
		} else {
			System.err.println("[API] Fehler WorldAPI - Generat!");
		}
	}

}
