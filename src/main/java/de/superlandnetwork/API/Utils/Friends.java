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

package de.superlandnetwork.API.Utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.superlandnetwork.API.PlayerAPI.PlayerAPI;

public class Friends {

	private String UUID = null;
	private String Username = null;
	private boolean Online = false;
	
	public Friends(String UUID, boolean Online) {
		PlayerAPI playerapi = new PlayerAPI(java.util.UUID.fromString(UUID));
		this.UUID = UUID;
		this.Username = playerapi.getUsername();
		this.Online = Online;
	}
	
	public Friends(String UUID) {
		PlayerAPI playerapi = new PlayerAPI(java.util.UUID.fromString(UUID));
		this.UUID = UUID;
		this.Username = playerapi.getUsername();
		this.Online = playerapi.isOnline();
	}
	
	public ItemStack getHead() {
		String prefix = getPrefix();
		ArrayList<String> Lore = new ArrayList<>();
		if(Online)
			Lore.add("§aOnline");
		else
			Lore.add("§7Zulezt §7Online §7vor " + new PlayerAPI(java.util.UUID.fromString(UUID)).getLastOnlineString());
		ItemStack item = null;
		if(Online)
			item = new ItemBuilder().getSkinnedHead(prefix, Username, Lore);
		else
			item = new ItemBuilder().getItem(prefix, Material.SKULL_ITEM, 1, Lore);
		return item;
	}
	
	private String getPrefix() {
		PlayerAPI playerAPI = new PlayerAPI(java.util.UUID.fromString(UUID));
		return playerAPI.getTabPrefix();
	}

	public String getUUID() {
		return UUID;
	}
	
	public String getUsername() {
		return Username;
	}
	
	public boolean isOnline() {
		return Online;
	}
	
}
