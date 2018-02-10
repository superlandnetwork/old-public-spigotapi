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

package de.superlandnetwork.API.ServerAPI;

import de.superlandnetwork.API.API;

public class ServerAPI {

	private Server server;

	public ServerAPI(Server server) {
		this.server = server;
	}

	public void update() {
		int isOn = 0;
		if (server.isOnline())
			isOn = 1;
		API.getInstance().getMySQL()
				.update("UPDATE `SLN_MC_Servers` SET `StatusID`='" + server.getStatusID() + "',`GameTypeID`='"
						+ server.getGameTypeID() + "',`MapID`='" + server.getMapID() + "',`isOnline`='" + isOn
						+ "',`Players_Online`='" + server.getPlayers_Online() + "',`Players_Max`='"
						+ server.getPlayers_Max() + "' WHERE `GameID`='" + server.getGameID() + "' AND `ServerID`='"
						+ server.getServerID() + "'");
	}

}
