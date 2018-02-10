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

package de.superlandnetwork.API.StatsAPI;

public enum StatsEnum {

	/* KFFA */
	KFFA("SLN_Stats_KFFA", 0), 
	KFFA_KILLS("SLN_Stats_KFFA", 1), 
	KFFA_DEATHS("SLN_Stats_KFFA", 2),

	/* SuperVaro */
	SuperVaro("SLN_Stats_SuperVaro", 0), 
	SuperVaro_KILLS("SLN_Stats_SuperVaro", 1), 
	SuperVaro_DEATHS("SLN_Stats_SuperVaro", 2), 
	SuperVaro_RoundWin("SLN_Stats_SuperVaro", 3),
	SuperVaro_RoundLose("SLN_Stats_SuperVaro", 4),
	SuperVaro_Games("SLN_Stats_SuperVaro", 5),

	/* BedWars */
	BedWars("SLN_Stats_BedWars", 0), 
	BedWars_KILLS("SLN_Stats_BedWars", 1),
	BedWars_DEATHS("SLN_Stats_BedWars", 2), 
	BedWars_RoundWin("SLN_Stats_BedWars", 3),
	BedWars_RoundLose("SLN_Stats_BedWars", 4),
	BedWars_Games("SLN_Stats_BedWars", 5),

	/* SG */
	SG("SLN_Stats_SurvivalGames", 0),
	SG_KILLS("SLN_Stats_SurvivalGames", 1),
	SG_DEATHS("SLN_Stats_SurvivalGames", 2),
	SG_RoundWin("SLN_Stats_SurvivalGames", 3),
	SG_RoundLose("SLN_Stats_SurvivalGames", 4),
	SG_Games("SLN_Stats_SurvivalGames", 5),

	
	/* QSG */
	QSG("SLN_Stats_QuickSurvivalGames", 0),
	QSG_KILLS("SLN_Stats_QuickSurvivalGames", 1),
	QSG_DEATHS("SLN_Stats_QuickSurvivalGames", 2),
	QSG_RoundWin("SLN_Stats_QuickSurvivalGames", 3),
	QSG_RoundLose("SLN_Stats_QuickSurvivalGames", 4),
	QSG_Games("SLN_Stats_QuickSurvivalGames", 5),

	
	/* FairGhast */
	FairGhast("SLN_Stats_FairGhast", 0),
	//
	FairGhast_RoundWin("SLN_Stats_FairGhast", 3),
	FairGhast_RoundLose("SLN_Stats_FairGhast", 4),
	FairGhast_Games("SLN_Stats_FairGhast", 5),

	
	/* OITC */
	OITC("SLN_Stats_OITC", 0),
	OITC_KILLS("SLN_Stats_OITC", 1),
	OITC_DEATHS("SLN_Stats_OITC", 2), 
	OITC_RoundWin("SLN_Stats_OITC", 3),
	OITC_RoundLose("SLN_Stats_OITC", 4),
	OITC_Games("SLN_Stats_OITC", 5),

	/* GunGame */
	GUNGAME("SLN_Stats_GunGame", 0), 
	GUNGAME_KILLS("SLN_Stats_GunGame", 1), 
	GUNGAME_DEATHS("SLN_Stats_GunGame", 2);
	
	private String DB_NAME;
	private int id;
	
	private StatsEnum(String s, int i) {
		DB_NAME = s;
		id = i;
	}
	
	public String getDB_NAME() {
		return DB_NAME;
	}
	
	public int getId() {
		return id;
	}
}
