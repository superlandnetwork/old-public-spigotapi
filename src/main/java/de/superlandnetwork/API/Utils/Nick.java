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

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.superlandnetwork.API.API;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class Nick {

	Player p;
	String nick;
	boolean isNicked;
	String Realname;

	public Nick(Player p) {
		this.p = p;
		setNicked(false);
		setNick(null);
		Realname = p.getName();
	}

	public boolean nick() {
		if (!isNicked()) {
			String name = getRDMNick();
			if (API.getInstance().UsedNicks.contains(name))
				return false;
			setNicked(true);
			setNick(name);
			setName(((CraftPlayer)p), name);
			setSkin(((CraftPlayer)p), getSkin());
			setUUID(((EntityHuman) ((CraftPlayer)p).getHandle()), getSkin());
			API.getInstance().UsedNicks.add(name);
			return true;
		}
		return false;
	}

	/**
	 * @return uuid or null
	 */
	public UUID getSkin() {
		ResultSet rs = API.getInstance().getMySQL()
				.getResult("SELECT UUID FROM `Nick` WHERE NickName='" + getNick() + "'");
		try {
			while (rs.next()) {
				return UUID.fromString(rs.getString("UUID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean unNick() {
		if (isNicked()) {
			setNicked(false);
			setNick(null);
			setName(((CraftPlayer)p), Realname);
			setSkin(((CraftPlayer)p), p.getUniqueId());
			setUUID(((EntityHuman) ((CraftPlayer)p).getHandle()), p.getUniqueId());
			return true;
		}
		return false;
	}

	/**
	 * @param Skin
	 */
	public void setSkin(CraftPlayer cp, UUID Skin) {
		GameProfile gp = cp.getProfile();
		try {
			gp = GameProfileBuilder.fetch(Skin);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		Collection<Property> props = gp.getProperties().get("textures");
		cp.getProfile().getProperties().removeAll("textures");
		cp.getProfile().getProperties().putAll("textures", props);
		addTabList(cp);
		removeTabList(cp);
	}

	/**
	 * @param name
	 */
	public void setName(CraftPlayer cp, String name) {
		try {
			Field field = cp.getProfile().getClass().getDeclaredField("name");
			field.setAccessible(true);
			field.set(cp.getProfile(), name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param packet
	 */
	public void sendPacket(Packet<?> packet) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if(all.getUniqueId() == p.getUniqueId()) continue;
				((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
		}
	}

	/**
	 * @param player
	 */
	public void addTabList(CraftPlayer player) {
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,
				player.getHandle());
		sendPacket(packet);
	}
	
	public void setUUID(EntityHuman player, UUID uuid) {
		PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn(player);
		try {
			Field field2 = packet.getClass().getDeclaredField("b");
			field2.setAccessible(true);
			field2.set(packet, uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param player
	 */
	public void removeTabList(CraftPlayer player) {
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,
				player.getHandle());
		sendPacket(packet);
	}

	/**
	 * @return name
	 */
	public String getRDMNick() {
		int i = API.getInstance().randomInt(0, API.getInstance().AllNicksDB.size() - 1);
		return API.getInstance().AllNicksDB.get(i);
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick
	 *            the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the isNicked
	 */
	public boolean isNicked() {
		return isNicked;
	}

	/**
	 * @param isNicked
	 *            the isNicked to set
	 */
	public void setNicked(boolean isNicked) {
		this.isNicked = isNicked;
	}

}
