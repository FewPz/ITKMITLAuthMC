package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.peeranat.mc.ITAuthMain;

public class OnPlayerQuit implements Listener {

	private ITAuthMain instance;
	
	public OnPlayerQuit(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void OnPlayerQuitEvent(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		instance.getPlayerHandler().removePlayer(player);
	}
	
}
