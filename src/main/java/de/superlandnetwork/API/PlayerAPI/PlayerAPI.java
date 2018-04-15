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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import de.superlandnetwork.API.API;

public class PlayerAPI {

	private UUID UUID;
	private static String User_Tabel = "Users";

	/**
	 * @param UUID
	 */
	public PlayerAPI(UUID UUID) {
		this.UUID = UUID;
	}

	public void InsertUserInDB() {
		if (!IsUserInDB1())
			API.getInstance().getMySQL().update("INSERT INTO " + User_Tabel + " (UUID) VALUES ('" + this.UUID + ")");
	}

	public static boolean IsUserInDB(String name) {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT UUID From " + User_Tabel + " WHERE usernanme='" + name + "'");
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean IsUserInDB1() {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT id From " + User_Tabel + " WHERE UUID='" + UUID + "'");
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getUUID(String name) {
		if (IsUserInDB(name)) {
			ResultSet rs = API.getInstance().getMySQL()
					.getResult("SELECT UUID From " + User_Tabel + " WHERE usernanme='" + name + "'");
			try {
				while (rs.next()) {
					return rs.getString("UUID");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public String getUsername() {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT usernanme From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				return rs.getString("usernanme");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean AutoNick() {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT AutoNick From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				if (rs.getInt("AutoNick") == 1) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setAutoNick(int i) {
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET AutoNick='" + i + "' WHERE UUID='" + this.UUID + "'");
	}

	public boolean isBanned() {
		
		InsertUserInDB();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Baned From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				if (rs.getInt("Baned") == 1)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void Ban(String reason, long second, String byUUID) {
		InsertUserInDB();
		long end = 0;
		if (second == -1) {
			end = -1;
		} else {
			long current = System.currentTimeMillis();
			long millis = second * 1000;
			end = current + millis;
		}
		API.getInstance().getMySQL().update("UPDATE " + User_Tabel + " SET Ban_Ende='" + end + "', Ban_BanByUUID='"
				+ byUUID + "', Ban_Grund='" + reason + "', Baned='1' WHERE UUID='" + this.UUID + "'");
		if (Bukkit.getPlayer(this.UUID) != null) {
			Bukkit.getPlayer(this.UUID).kickPlayer("§cDu wurdest vom SuperLandNetwork.de Netzwerk gebannt\n"
					+ "\n"
					+ "§6Grund: §c"+ reason + "\n"
					+ "\n"
					+ "§7Wenn du einen Entbannungsantrag stellen möchtest,\n"
					+ "§7kannst du in unser Forum gehen\n"
					+ "§eForum.SuperLandNetwork.de");
		}
	}

	/**
	 * @param Coins
	 */
	public void setCoins(int Coins) {
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET Coins='" + Coins + "' WHERE UUID='" + this.UUID + "'");
	}

	/**
	 * @return int
	 */
	public int getCoins() {
		InsertUserInDB();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Coins From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				return rs.getInt("Coins");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param Coins
	 */
	public void addCoins(int Coins) {
		int coin = getCoins();
		int Coins2 = coin + Coins;
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET Coins='" + Coins2 + "' WHERE UUID='" + this.UUID + "'");
	}

	/* ------ [ Money System ] ------ */

	/**
	 * @param Money
	 */
	public void setMoney(int Money) {
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET Money='" + Money + "' WHERE UUID='" + this.UUID + "'");
	}

	/**
	 * @return int
	 */
	public int getMoney() {
		InsertUserInDB();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Money From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				return rs.getInt("Money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param Money
	 */
	public void addMoney(int Money) {
		int mone = getMoney();
		int Money2 = mone + Money;
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET Money='" + Money2 + "' WHERE UUID='" + this.UUID + "'");
	}

	/* ------ [ Permissons System ] ------ */

	/**
	 * @param Group_ID
	 */
	public void SetPlayerGroup(int Group_ID) {
		InsertUserInDB();
		API.getInstance().getMySQL()
				.update("UPDATE " + User_Tabel + " SET rank='" + Group_ID + "' WHERE UUID='" + this.UUID + "'");
	}

	/**
	 * @return int
	 */
	public int getPlayerGroup() {
		InsertUserInDB();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT rank From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				return rs.getInt("rank");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * @param GroupID
	 * @return boolean
	 */
	public boolean IsPlayerInGroup(int GroupID) {
		if (getPlayerGroup() == GroupID)
			return true;
		return false;
	}

	/**
	 * @return String
	 */
	public String getChatPrefix() {
		String name = "";
		if(Bukkit.getPlayer(UUID) == null)
			name = getUsername();
		else 
			name = Bukkit.getPlayer(UUID).getDisplayName();
		for (PermEnum e : PermEnum.values()) {
			if(IsPlayerInGroup(e.getId())) {
				return e.getPrefix() + name + "§f";
			} else continue;
		}
		return name;
	}
	
	public String getTabPrefix() {
		String name = "";
		if(Bukkit.getPlayer(UUID) == null)
			name = getUsername();
		else 
			name = Bukkit.getPlayer(UUID).getDisplayName();
		for (PermEnum e : PermEnum.values()) {
			if(IsPlayerInGroup(e.getId())) {
				return e.getTabList() + name + "§f";
			} else continue;
		}
		return name;
	}
	
	public ArrayList<String> getFriendsUUID() {
		InsertUserInDB();
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Friend_UUID From Friends WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				String uuid = rs.getString("Friend_UUID");
				if (new PlayerAPI(java.util.UUID.fromString(uuid)).isOnline())
					list.add(uuid);
				else
					list2.add(uuid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		for(String s : list2) {
			list.add(s);
		}
		return list;
	}
	
	public ArrayList<String> getFriendsUUIDOrdet() {
		ArrayList<String> list = getFriendsUUID();
		ArrayList<String> list2 = new ArrayList<>();
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.ADMINISTRATOR.getId()) || api.IsPlayerInGroup(PermEnum.ADMINISTRATORIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.DEVELOPER.getId()) || api.IsPlayerInGroup(PermEnum.DEVELOPERIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.SRMODERATOR.getId()) || api.IsPlayerInGroup(PermEnum.SRMODERATORIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.MODERATOR.getId()) || api.IsPlayerInGroup(PermEnum.MODERATORIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.SUPPORTER.getId()) || api.IsPlayerInGroup(PermEnum.SUPPORTERIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.BUILDER.getId()) || api.IsPlayerInGroup(PermEnum.BUILDERIN.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.YOUTUBER.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.PREMIUMPLUS.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.PREMIUM.getId())) {
				if (api.isOnline()) {
					list2.add(list.get(i));
					list.remove(i);
				} else continue;
			}
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.isOnline()) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.ADMINISTRATOR.getId()) || api.IsPlayerInGroup(PermEnum.ADMINISTRATORIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.DEVELOPER.getId()) || api.IsPlayerInGroup(PermEnum.DEVELOPERIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.SRMODERATOR.getId()) || api.IsPlayerInGroup(PermEnum.SRMODERATORIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.MODERATOR.getId()) || api.IsPlayerInGroup(PermEnum.MODERATORIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.SUPPORTER.getId()) || api.IsPlayerInGroup(PermEnum.SUPPORTERIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.BUILDER.getId()) || api.IsPlayerInGroup(PermEnum.BUILDERIN.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.YOUTUBER.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.PREMIUMPLUS.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (int i=0; i<list.size(); i++) {
			PlayerAPI api = new PlayerAPI(java.util.UUID.fromString(list.get(i)));
			if (api.IsPlayerInGroup(PermEnum.PREMIUM.getId())) {
				list2.add(list.get(i));
				list.remove(i);
			} else continue;
		}
		for (String s : list) {
			list2.add(s);
		}
		return list2;
	}
	
	public ArrayList<String> getFriendRequests() {
		InsertUserInDB();
		ArrayList<String> list = new ArrayList<>();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Requester_UUID From FriendRequests WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				list.add(rs.getString("Requester_UUID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean isOnline() {
		InsertUserInDB();
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT Online From " + User_Tabel + " WHERE UUID='" + this.UUID + "'");
		try {
			while (rs.next()) {
				if(rs.getInt("Online") == 1)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public long getLastOnline() {
		ResultSet rs = API.getInstance().getMySQL().getResult("SELECT Friend_LastOnline From Users WHERE UUID='"+this.UUID+"'");
		try {
			if(rs.next()) {
				return rs.getLong("Friend_LastOnline");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getLastOnlineString() {
		long current = System.currentTimeMillis();
		long end = getLastOnline();
		
		if (end == 0) 
			return "Error";
		
		long different = end - current;
		
		long secounds = 0;
		long minutes = 0;
		long hours = 0;
		long days = 0;
		while(different > 1000){
			different -=1000;
			secounds ++;
		}
		while(secounds > 60){
			secounds -=60;
			minutes ++;
		}
		while(minutes > 60){
			minutes -=60;
			hours ++;
		}
		while(hours > 24){
			hours -=24;
			days ++;
		}
		if(days >= 1){
			return "§7" + days + " §7Tage, §7" + hours + " §7Stunden, §7" + minutes + " §7Minuten, §7" + secounds + " §7 Sekunden";
		}
		if(hours >= 1){
			return "§7" + hours + " §7Stunden, §7" + minutes + " §7Minuten, §7" + secounds + " §7 Sekunden";
		}
		if(minutes >= 1){
			return "§7" + minutes + " §7Minuten, §7" + secounds + " §7 Sekunden";
		}
		if(secounds >= 1){
			return "§7" + secounds + " §7 Sekunden";
		}
		return "ERROR";
	}

	
	public long getOnlineTime() {
		ResultSet rs = API.getInstance().getMySQL().getResult("SELECT OnlineTime From Users WHERE UUID='"+this.UUID+"'");
		try {
			if(rs.next()) {
				return rs.getLong("OnlineTime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
