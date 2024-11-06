package com.peeranat.mc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.peeranat.mc.player.ITPlayer;

public class PlayerAuthencationEvent extends Event {

	private ITPlayer itplayer;
	private Player player;

	// Constants
	private static final HandlerList HANDLERS = new HandlerList();
	
	public PlayerAuthencationEvent(Player player, ITPlayer itplayer) {
		this.player = player;
		this.itplayer = itplayer;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ITPlayer getITPlayer() {
		return itplayer;
	}

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
