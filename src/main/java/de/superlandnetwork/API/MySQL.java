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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	public static Connection con;

	public void connect() {
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://" + API.getInstance().MySQL_Host + ":" + API.getInstance().MySQL_Port + "/"
								+ API.getInstance().MySQL_Database + "?autoReconnect=true",
						API.getInstance().MySQL_Username, API.getInstance().MySQL_Password);
				System.out.println("[API] MySQL Verbindung Aufgebaut");
			} catch (SQLException e) {
				System.err.println("[API] MySQL - Connect Error");
				e.printStackTrace();
			}
		}
	}

	public void close() {
		if (isConnected()) {
			try {
				con.close();
				System.out.println("[API] MySQL Verbindung Geschlossen");
			} catch (SQLException e) {
				System.err.println("[API] MySQL - Close Error");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return true or false
	 */
	public boolean isConnected() {
		return con != null;
	}

	/**
	 * @param query
	 */
	public void update(String query) {
		if (isConnected()) {
			try {
				Statement st = con.createStatement();
				st.executeUpdate(query);
				st.close();
			} catch (SQLException e) {
				System.err.println("[API] MySQL - Update Error");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param query
	 * @return rs
	 */
	public ResultSet getResult(String query) {
		ResultSet rs = null;
		if (isConnected()) {
			try {
				Statement st = con.createStatement();
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				System.err.println("[API§7] MySQL - Result Error");
				e.printStackTrace();
			}
		}
		return rs;
	}

}
