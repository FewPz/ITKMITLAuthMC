package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnPlayerInteractAtEntity implements Listener {

	private ITAuthMain instance;

	public OnPlayerInteractAtEntity(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void OnPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
		Player player = e.getPlayer();
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null || itplayer.isLoggedIn()) {
			return;
		}
		e.setCancelled(true);
	}
	
}