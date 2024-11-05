package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

import io.papermc.paper.event.player.PlayerPickItemEvent;

public class OnPlayerPickupItem implements Listener {

	private ITAuthMain instance;

	public OnPlayerPickupItem(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)	
	public void OnPlayerPickupItemEvent(PlayerPickItemEvent e) {
		Player player = e.getPlayer();
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null || itplayer.isLoggedIn()) {
			return;
		}
		e.setCancelled(true);
	}
	
}