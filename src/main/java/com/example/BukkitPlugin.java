package com.example;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		// Get the Vault permission provider.
		RegisteredServiceProvider<Permission> permissionRSP = this.getServer().getServicesManager().getRegistration(Permission.class);
		if (permissionRSP == null || permissionRSP.getProvider() == null) {
			this.getLogger().warning("Vault is required to use this plugin!");
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		// Set the command executor.
		this.getCommand("hello").setExecutor(new HelloCommand(permissionRSP.getProvider()));
	}

}
