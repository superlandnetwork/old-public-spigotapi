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

package de.superlandnetwork.API.ServerAPI;

public class Server {

	/* GameID's: */
	/* 1 - KFFA */
	/* 2 - BedWars */
	/* 3 - TTT */
	/* 4 - OITC */
	/* 5 - SurvivalGames */
	/* 6 - QuickSurvivalGames */
	/* 7 - FairGhast */
	/* 8 - SuperVaro */
	/* 9 - SkyWars */
	/* 10 - GunGame */

	/* StatusID's: */
	/* 1 - Lobby / OK */
	/* 2 - InGame */
	/* 3 - Reboot */

	/* GameTypeID's: */
	/* 1 - 4x4 / Normal */
	/* 2 - 8x1 */

	private int GameID;
	private int StatusID;
	private int GameTypeID;
	private int ServerID;
	private int MapID;
	private boolean isOnline;
	private int Players_Online;
	private int Players_Max;

	public Server(int GameID, int StatusID, int GameTypeID, int ServerID, int MapID, boolean isOnline, int Players_Online, int Players_Max) {
		this.GameID = GameID;
		this.StatusID = StatusID;
		this.GameTypeID = GameTypeID;
		this.ServerID = ServerID;
		this.MapID = MapID;
		this.isOnline = isOnline;
		this.Players_Online = Players_Online;
		this.Players_Max = Players_Max;
	}

	public int getGameID() {
		return GameID;
	}

	public int getStatusID() {
		return StatusID;
	}

	public void setStatusID(int statusID) {
		StatusID = statusID;
	}
	
	public int getGameTypeID() {
		return GameTypeID;
	}
	
	public void setGameTypeID(int gameTypeID) {
		GameTypeID = gameTypeID;
	}

	public int getServerID() {
		return ServerID;
	}

	public int getMapID() {
		return MapID;
	}

	public void setMapID(int mapID) {
		MapID = mapID;
	}
	
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getPlayers_Online() {
		return Players_Online;
	}

	public void setPlayers_Online(int players_Online) {
		Players_Online = players_Online;
	}

	public int getPlayers_Max() {
		return Players_Max;
	}
	
	public void setPlayers_Max(int players_Max) {
		Players_Max = players_Max;
	}

}
