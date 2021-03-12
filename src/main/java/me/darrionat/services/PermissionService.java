package me.darrionat.services;

import org.bukkit.entity.Player;

import me.darrionat.repositories.CommandPermissionRepository;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.Node;

public class PermissionService {
	private CommandPermissionRepository commandPermissionRepository;
	private String defaultGroup;

	public PermissionService(ConfigService configService, CommandPermissionRepository commandPermissionRepository) {
		this.commandPermissionRepository = commandPermissionRepository;
		this.defaultGroup = configService.getDefaultGroup();
	}

	public boolean permissionDefined(String label) {
		return commandPermissionRepository.getPermission(label) != null;
	}

	public boolean playerCanUseCommand(Player p, String label) {
		return p.hasPermission(commandPermissionRepository.getPermission(label));
	}

	public String getPermission(String label) {
		return commandPermissionRepository.getPermission(label);
	}

	public boolean defaultHasPermission(String permission) {
		LuckPerms api = LuckPermsProvider.get();
		Group g = api.getGroupManager().getGroup(defaultGroup);

		for (Node node : g.getNodes()) {
			if (node.getKey().equals(permission))
				return true;
		}
		return false;
	}
}