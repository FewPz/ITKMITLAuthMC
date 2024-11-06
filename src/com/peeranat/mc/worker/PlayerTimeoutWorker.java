package com.peeranat.mc.worker;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.player.ITPlayer;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class PlayerTimeoutWorker implements Runnable {
	
	private ITAuthMain instance;
	
	public PlayerTimeoutWorker(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
			if (itplayer == null) {
				continue;
			}
			if (itplayer.isLoggedIn()) {
				continue;
			}
			if (itplayer.getTimeout() % 10 == 0) {
				player.sendMessage(ChatColor.RED + "You have " + ChatColor.BOLD + itplayer.getTimeout() + ChatColor.RESET + ChatColor.RED + " seconds to log in");
				player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 1, itplayer.getTimeout() / 60.0f);
			}
			itplayer.decrementTimeout();
			if (itplayer.getTimeout() < 0) {
				player.removePotionEffect(PotionEffectType.BLINDNESS);
				player.kick(Component.text("It's been 60 seconds and you haven't logged in yet. Good bye!"));
			}
		}
	}

}
