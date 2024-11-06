package com.peeranat.mc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.peeranat.mc.ITAuthMain;
import com.peeranat.mc.events.PlayerAuthencationEvent;
import com.peeranat.mc.lib.AuthLib;
import com.peeranat.mc.lib.AuthResponse;
import com.peeranat.mc.player.ITPlayer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ChatColor;

public class LoginCmd implements CommandExecutor {

	private ITAuthMain instance;
	public LoginCmd(ITAuthMain instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;
		ITPlayer itplayer = instance.getPlayerHandler().getPlayer(player.getName());
		if (itplayer == null) {
			return false;
		}
		if (args.length == 2) {
			String account = args[0];
			String password = args[1];
			AuthResponse response = AuthLib.sendPostRequest(account, password);
			if (response == null) {
				player.kick(Component.text("Login failed. Please try again later.").color(NamedTextColor.RED));
				return false;
			}
			itplayer.setLoggedIn(true);
			
			player.sendMessage(ChatColor.GREEN + "Login successful!");
			player.sendMessage(ChatColor.GREEN + "Welcome back, " + ChatColor.YELLOW + response.getFirstname() + " " + response.getLastname() + "!");
			
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 1, 1);
			player.removePotionEffect(PotionEffectType.BLINDNESS);
			
			// Call the PlayerAuthencationEvent
			Bukkit.getPluginManager().callEvent(new PlayerAuthencationEvent(player, itplayer));
		} else {
			player.sendMessage(ChatColor.RED + "Usage: /login <itkmitl-account> <password>");	
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 1, 0);
		}
		return false;
	}

}
