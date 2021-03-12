package me.darrionat.services;

import org.bukkit.configuration.file.FileConfiguration;

import me.darrionat.StaffWarn;

public class ConfigService {

	private StaffWarn plugin;
	private FileConfiguration config;

	public ConfigService(StaffWarn plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		this.config = plugin.getConfig();
	}

	public String getDefaultGroup() {
		return config.getString("defaultGroup");
	}
}
