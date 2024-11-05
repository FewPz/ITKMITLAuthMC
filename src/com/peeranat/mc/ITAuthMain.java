package com.peeranat.mc;

import org.bukkit.plugin.java.JavaPlugin;

public class ITAuthMain extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("ITAuth has been enabled!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ITAuth has been disabled!");
	}

}
