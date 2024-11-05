package com.peeranat.mc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.peeranat.mc.cmd.LoginCmd;
import com.peeranat.mc.listener.OnBlockBreak;
import com.peeranat.mc.listener.OnBlockPlace;
import com.peeranat.mc.listener.OnEntityDamageByEntity;
import com.peeranat.mc.listener.OnInventoryClick;
import com.peeranat.mc.listener.OnPlayerAirChange;
import com.peeranat.mc.listener.OnPlayerBedEnter;
import com.peeranat.mc.listener.OnPlayerChat;
import com.peeranat.mc.listener.OnPlayerCommandPreprocess;
import com.peeranat.mc.listener.OnPlayerDropItem;
import com.peeranat.mc.listener.OnPlayerEditBook;
import com.peeranat.mc.listener.OnPlayerInteract;
import com.peeranat.mc.listener.OnPlayerInteractAtEntity;
import com.peeranat.mc.listener.OnPlayerInteractEntity;
import com.peeranat.mc.listener.OnPlayerItemConsume;
import com.peeranat.mc.listener.OnPlayerItemHeld;
import com.peeranat.mc.listener.OnPlayerJoin;
import com.peeranat.mc.listener.OnPlayerMove;
import com.peeranat.mc.listener.OnPlayerPickupItem;
import com.peeranat.mc.listener.OnPlayerQuit;
import com.peeranat.mc.listener.OnPlayerShearEntity;
import com.peeranat.mc.listener.OnPlayerSwapHandItems;
import com.peeranat.mc.listener.OnSignChange;
import com.peeranat.mc.player.PlayerHandler;
import com.peeranat.mc.worker.PlayerTimeoutWorker;

public class ITAuthMain extends JavaPlugin {

	private static ITAuthMain instance;
	private PlayerHandler playerHandler;

	@Override
	public void onEnable() {
		getLogger().info("ITAuth has been enabled!");

		saveDefaultConfig();
		
		getConfig().addDefault("AUTH_URL", "<<URL_AUTH>>");
		
		getConfig().options().copyDefaults(true);
		saveConfig();

		instance = this;

		playerHandler = new PlayerHandler();

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new OnPlayerJoin(this), this);
		pm.registerEvents(new OnPlayerQuit(this), this);
		pm.registerEvents(new OnPlayerAirChange(this), this);
		pm.registerEvents(new OnPlayerSwapHandItems(this), this);
		pm.registerEvents(new OnBlockBreak(this), this);
		pm.registerEvents(new OnBlockPlace(this), this);
		pm.registerEvents(new OnPlayerMove(this), this);
		pm.registerEvents(new OnPlayerChat(this), this);
		pm.registerEvents(new OnPlayerCommandPreprocess(this), this);
		pm.registerEvents(new OnPlayerInteract(this), this);
		pm.registerEvents(new OnPlayerInteractEntity(this), this);
		pm.registerEvents(new OnPlayerInteractAtEntity(this), this);
		pm.registerEvents(new OnEntityDamageByEntity(this), this);
		pm.registerEvents(new OnPlayerShearEntity(this), this);
		pm.registerEvents(new OnPlayerBedEnter(this), this);
		pm.registerEvents(new OnPlayerEditBook(this), this);
		pm.registerEvents(new OnSignChange(this), this);
		pm.registerEvents(new OnPlayerPickupItem(this), this);
		pm.registerEvents(new OnPlayerDropItem(this), this);
		pm.registerEvents(new OnPlayerItemHeld(this), this);
		pm.registerEvents(new OnPlayerItemConsume(this), this);
		pm.registerEvents(new OnInventoryClick(this), this);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PlayerTimeoutWorker(this), 0, 20);

		getCommand("login").setExecutor(new LoginCmd(this));
	}

	@Override
	public void onDisable() {
		getLogger().info("ITAuth has been disabled!");
	}

	public PlayerHandler getPlayerHandler() {
		return playerHandler;
	}

	public static ITAuthMain getInstance() {
		return instance;
	}

}
