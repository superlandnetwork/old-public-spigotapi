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

public enum PermEnum {

	SPIELER(1, "§aSpieler §7| §a", "§aSpieler", "§a"),
	PREMIUM(2, "§6Premium §7| §6", "§6Premium", "§6"),
	PREMIUMPLUS(3, "§6Premium §7| §6", "§6Premium+", "§6"),
	YOUTUBER(4, "§5YouTuber §7| §5", "§5YouTuber", "§5YT §7| §5"),
	SUPPORTER(5, "§9Supporter §7| §9", "§9Supporter", "§9Sup §7| §9"),
	SUPPORTERIN(6, "§9Supporterin §7| §9", "§9Supporterin", "§9Sup §7| §9"),
	MODERATOR(7, "§cModerator §7| §c", "§cModerator", "§cMod §7| §c"),
	MODERATORIN(8, "§cModeratorin §7| §c", "§cModeratorin", "§cMod §7| §c"),
	SRMODERATOR(9, "§cSrModerator §7| §c", "§cSrModerator", "§cSrMod §7| §c"),
	SRMODERATORIN(10, "§cSrModeratorin §7| §c", "§cSrModeratorin", "§cSrMod §7| §c"),
	BUILDER(11, "§eBuilder §7| §e", "§eBuilder", "§eBuilder §7| §e"),
	BUILDERIN(12, "§eBuilderin §7| §e", "§eBuilderin", "§eBuilder §7| §e"),
	HELFER(13, "§3Helfer §7| §3", "§3Helfer", "§3Helfer §7| §3"),
	HELFERIN(14, "§33Helferin §7| §3", "§3Helferin", "§3Helfer §7| §3"),
	DEVELOPER(15, "§bDeveloper §7| §b", "§bDeveloper", "§bDev §7| §b"),
	DEVELOPERIN(16, "§bDeveloperin §7| §b", "§bDeveloperin", "§bDev §7| §b"),
	ADMINISTRATOR(17, "§4Administrator §7| §4", "§4Administator", "§4Admin §7| §4"),
	ADMINISTRATORIN(18, "§4Administratorin §7| §4", "§4Administratorin", "§4Admin §7| §4"),
	OWNER(19, "§4Owner §7| §4", "§4Owner", "§4Owner §7| §4"),
	OWNERIN(20, "§4Ownerin §7| §4", "§4Ownerin", "§4Ownerin §7| §4");

	
	int id;
	String Prefix;
	String Name;
	String TabList;
	
	private PermEnum(int id, String prefix, String Name, String Tablist) {
		this.id = id;
		this.Prefix = prefix;
		this.Name = Name;
		this.TabList = Tablist;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return Prefix;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * @return the tabList
	 */
	public String getTabList() {
		return TabList;
	}
}
