package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnBlockPlace implements Listener {

	private ITAuthMain instance;

	public OnBlockPlace(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(ignoreCancelled = true)	
	public void OnBlockPlaceEvent(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null || itplayer.isLoggedIn()) {
			return;
		}
		e.setCancelled(true);
	}
	
}
