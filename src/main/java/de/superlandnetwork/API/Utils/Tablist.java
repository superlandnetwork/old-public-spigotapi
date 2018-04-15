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

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Tablist {

	/**
	 * @param player
	 * @param Header
	 * @param Footer
	 */
	public Tablist(Player player, String Header, String Footer) {
		if (Header == null) {
			Header = "";
		}
		if (Footer == null) {
			Footer = "";
		}
		Header = ChatColor.translateAlternateColorCodes('&', Header);
		Footer = ChatColor.translateAlternateColorCodes('&', Footer);

		Header = Header.replaceAll("%PLAYER%", player.getDisplayName());
		Footer = Footer.replaceAll("%PLAYER%", player.getDisplayName());

		PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;

		IChatBaseComponent header = ChatSerializer.a("{\"text\": \"" + Header + "\"}");
		IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"" + Footer + "\"}");

		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

		try {
			Field f = packet.getClass().getDeclaredField("a");
			f.setAccessible(true);
			f.set(packet, header);
			Field f2 = packet.getClass().getDeclaredField("b");
			f2.setAccessible(true);
			f2.set(packet, footer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.sendPacket(packet);
		}
	}
}
