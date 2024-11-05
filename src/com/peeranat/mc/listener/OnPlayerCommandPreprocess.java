package com.peeranat.mc.listener;

import java.util.ArrayList;
import java.util.Locale;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

public class OnPlayerCommandPreprocess implements Listener {

	private ITAuthMain instance;
	private ArrayList<String> commands;

	public OnPlayerCommandPreprocess(ITAuthMain instance) {
		this.instance = instance;
		this.commands = new ArrayList<String>();
		this.commands.add("/login");
		this.commands.add("/l");
		this.commands.add("/auth");
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void OnPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().split(" ")[0].toLowerCase(Locale.ROOT);
		Player player = e.getPlayer();
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null || itplayer.isLoggedIn()) {
			return;
		}
		if (commands.contains(cmd)) {
			return;
		}
		e.setCancelled(true);
	}
	
}
