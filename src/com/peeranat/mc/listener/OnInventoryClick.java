package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnInventoryClick implements Listener {
	
	private ITAuthMain instance;

	public OnInventoryClick(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)	
	public void OnInventoryClickEvent(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player player = (Player) e.getWhoClicked();
			ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
			if (itplayer == null || itplayer.isLoggedIn()) {
				return;
			}
			e.setCancelled(true);
		}
	}

}
