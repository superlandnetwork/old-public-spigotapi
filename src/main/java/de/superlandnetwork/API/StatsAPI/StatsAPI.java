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

package de.superlandnetwork.API.StatsAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import de.superlandnetwork.API.API;

public class StatsAPI {

	private UUID UUID;

	public StatsAPI(UUID UUID) {
		this.UUID = UUID;
	}

	public int getStates(StatsEnum stats) {
		if (stats.getId() == 1) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT Kills From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
			try {
				if (rs.next()) {
					return rs.getInt("Kills");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stats.getId() == 2) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT Deaths From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
			try {
				if (rs.next()) {
					return rs.getInt("Deaths");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stats.getId() == 3) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT Wins From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
			try {
				if (rs.next()) {
					return rs.getInt("Wins");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stats.getId() == 4) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT Loses From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
			try {
				if (rs.next()) {
					return rs.getInt("Loses");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stats.getId() == 5) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT Games From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
			try {
				if (rs.next()) {
					return rs.getInt("Games");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void setStates(StatsEnum stats, int value) {
		if (!UserInDB(stats))
			setUserInDB(stats, value);
		if (stats.getId() == 1) {
			API.getInstance().getMySQL()
					.update("UPDATE " + getGame(stats) + " SET Kills='" + value + "' WHERE UUID='" + this.UUID + "'");
		} else if (stats.getId() == 2) {
			API.getInstance().getMySQL()
					.update("UPDATE " + getGame(stats) + " SET Deaths='" + value + "' WHERE UUID='" + this.UUID + "'");
		} else if (stats.getId() == 3) {
			API.getInstance().getMySQL()
					.update("UPDATE " + getGame(stats) + " SET Wins='" + value + "' WHERE UUID='" + this.UUID + "'");
		} else if (stats.getId() == 4) {
			API.getInstance().getMySQL()
					.update("UPDATE " + getGame(stats) + " SET Loses='" + value + "' WHERE UUID='" + this.UUID + "'");
		} else if (stats.getId() == 5) {
			API.getInstance().getMySQL()
					.update("UPDATE " + getGame(stats) + " SET Games='" + value + "' WHERE UUID='" + this.UUID + "'");
		}
	}

	private boolean UserInDB(StatsEnum stats) {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT UUID From " + getGame(stats) + " WHERE UUID='" + this.UUID + "'");
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void setUserInDB(StatsEnum stats, int value) {
		if (stats.getId() == 1) {
			API.getInstance().getMySQL().update(
					"INSERT INTO " + getGame(stats) + " (UUID, Kills) VALUES ('" + this.UUID + "', '" + value + "')");
		} else if (stats.getId() == 2) {
			API.getInstance().getMySQL().update(
					"INSERT INTO " + getGame(stats) + " (UUID, Deaths) VALUES ('" + this.UUID + "', '" + value + "')");
		} else if (stats.getId() == 3) {
			API.getInstance().getMySQL().update(
					"INSERT INTO " + getGame(stats) + " (UUID, Wins) VALUES ('" + this.UUID + "', '" + value + "')");
		} else if (stats.getId() == 4) {
			API.getInstance().getMySQL().update(
					"INSERT INTO " + getGame(stats) + " (UUID, Loses) VALUES ('" + this.UUID + "', '" + value + "')");
		}
	}
	
	public void InsertUserInDB(StatsEnum stats) {
		if(!UserInDB(stats))
			API.getInstance().getMySQL().update("INSERT INTO " + getGame(stats) + " (UUID) VALUES ('" + this.UUID + "')");		
	}

	private String getGame(StatsEnum e) {
		return e.getDB_NAME();
	}

}
