package me.legitmodern.Nick;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import me.legitmodern.Nick.Nick;

public class TagApiListener implements Listener {

	Nick nick;
	
	public TagApiListener(Nick instance) {
		nick = instance;
	}
	
	@EventHandler
	public void onTagReceive(PlayerReceiveNameTagEvent event) {
		
		Player namedPlayer = event.getNamedPlayer();

        if (this.nick.getConfig().contains(namedPlayer.getName())) {
            event.setTag(this.nick.getConfig().getString(namedPlayer.getName()) + ChatColor.RESET);
        }
        else {
        	this.nick.logger.info("[Nick] An error occured whlist trying to use TagAPI for nickname change. Please report this ASAP!");
        }
	}
}