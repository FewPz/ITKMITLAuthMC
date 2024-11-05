package com.peeranat.mc.player;

import org.bukkit.entity.Player;

public class ITPlayer {
	
	private Player player;
	private boolean isLoggedIn;
	private int timeout = 62;
	
	public ITPlayer(Player player) {
		this.player = player;
		this.isLoggedIn = false;
	}	
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void decrementTimeout() {
		timeout--;
	}

}
