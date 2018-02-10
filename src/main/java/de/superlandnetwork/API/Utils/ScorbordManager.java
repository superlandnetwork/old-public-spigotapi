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

package de.superlandnetwork.API.Utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class ScorbordManager {

	private static HashMap<Player, Scoreboard> PlayerScorebord = new HashMap<Player, Scoreboard>();

	public static Scoreboard getScorebord(Player p) {
		if (PlayerScorebord.containsKey(p)) {
			return PlayerScorebord.get(p);
		}
		Scoreboard bord = Bukkit.getScoreboardManager().getNewScoreboard();
		PlayerScorebord.put(p, bord);
		return bord;
	}

}
