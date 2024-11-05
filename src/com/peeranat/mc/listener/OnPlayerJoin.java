package com.peeranat.mc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.peeranat.mc.ITAuthMain;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerJoin implements Listener {

	private ITAuthMain instance;
	
	public OnPlayerJoin(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void OnPlayerJoinEvent(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
//		player.sendTitle(Title.title(Component.text("Welcome Back").color(NamedTextColor.GOLD), Component.text("")));
		player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "=== [ IT Authencation ] ===");
		player.sendMessage("");
		player.sendMessage(ChatColor.AQUA + "* " + ChatColor.WHITE + "Please login using "+ ChatColor.YELLOW +"/login <itkmitl-account> <password>");
		player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "If you passed PSCP ratatype, you can pass this too XD");
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				player.showTitle(Title.title(Component.text("Welcome Back").color(NamedTextColor.GOLD), Component.text("Authencation Required")));
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1, false, false));
			}
			
		}.runTaskLater(instance, 10);
		
		instance.getPlayerHandler().addPlayer(player);
	}
	
}
