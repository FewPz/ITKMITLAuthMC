package com.peeranat.mc.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnPlayerMove implements Listener {

	private ITAuthMain instance;

	public OnPlayerMove(ITAuthMain instance) {
		this.instance = instance;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void OnPlayerMoveEvent(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null || itplayer.isLoggedIn()) {
			return;
		}
		Location from = e.getFrom();
		Location to = e.getTo();
		if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ() && from.getY() - to.getY() >= 0) {
			return;
		}
		e.setTo(e.getFrom());
	}
}
