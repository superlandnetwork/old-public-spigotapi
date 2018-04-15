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
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	/**
	 * @param name
	 * @return ItemStack
	 */
	public ItemStack getSkinnedHead(String name) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param Owner
	 * @return ItemStack
	 */
	public ItemStack getSkinnedHead(String name, String Owner) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(Owner);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param Owner
	 * @return ItemStack
	 */
	public ItemStack getSkinnedHead(String name, String Owner, ArrayList<String> list) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(Owner);
		meta.setDisplayName(name);
		meta.setLore(list);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type) {
		ItemStack item = new ItemStack(type);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount) {
		ItemStack item = new ItemStack(type, Ammount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @param Lore
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount, List<String> Lore) {
		ItemStack item = new ItemStack(type, Ammount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @param b
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount, byte b) {
		ItemStack item = new ItemStack(type, Ammount, b);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @param b
	 * @param Lore
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount, byte b, List<String> Lore) {
		ItemStack item = new ItemStack(type, Ammount, b);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @param b
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount, short b) {
		ItemStack item = new ItemStack(type, Ammount, b);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * @param name
	 * @param type
	 * @param Ammount
	 * @param b
	 * @param Lore
	 * @return ItemStack
	 */
	public ItemStack getItem(String name, Material type, int Ammount, short b, List<String> Lore) {
		ItemStack item = new ItemStack(type, Ammount, b);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

}
