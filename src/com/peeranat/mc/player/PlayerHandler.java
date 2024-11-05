package com.peeranat.mc.player;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class PlayerHandler {
	
	private ConcurrentHashMap<String, ITPlayer> players;
	
	public PlayerHandler() {
		players = new ConcurrentHashMap<String, ITPlayer>();
	}
	
	public void addPlayer(Player player) {
		players.put(player.getName(), new ITPlayer(player));
	}
	
	public void removePlayer(Player player) {
		players.remove(player.getName());
	}
	
	public ITPlayer getPlayer(String name) {
		return players.get(name);
	}

}
