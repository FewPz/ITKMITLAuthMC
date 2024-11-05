package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnEntityDamageByEntity implements Listener {
	
	private ITAuthMain instance;

	public OnEntityDamageByEntity(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void OnEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
			if (itplayer == null || itplayer.isLoggedIn()) {
				return;
			}
			e.setCancelled(true);
		}
	}
	
}
