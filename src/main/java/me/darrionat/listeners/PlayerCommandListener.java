package me.darrionat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.darrionat.StaffWarn;
import me.darrionat.services.MessageService;
import me.darrionat.services.PermissionService;

public class PlayerCommandListener implements Listener {
	private MessageService messageService;
	private PermissionService permissionService;

	public PlayerCommandListener(StaffWarn plugin, MessageService messageService, PermissionService permissionService) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.messageService = messageService;
		this.permissionService = permissionService;
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String label = getLabel(e.getMessage().replace("/", ""));

		if (!permissionService.permissionDefined(label))
			return;
		if (!permissionService.playerCanUseCommand(p, label))
			return;

		String permission = permissionService.getPermission(label);
		if (permissionService.defaultHasPermission(permission))
			return;

		messageService.alertPlayer(p, label, permission);
	}

	private String getLabel(String message) {
		String label;
		int i = message.indexOf(' ');
		try {
			label = message.substring(0, i);
		} catch (StringIndexOutOfBoundsException exe) {
			label = message;
		}
		return label;
	}

}