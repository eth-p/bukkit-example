package com.example;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloCommand implements CommandExecutor {

	private Permission permissions;

	public HelloCommand(Permission permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			this.helloConsole(sender);
			return true;
		}

		Player player = (Player) sender;
		String group = this.permissions.getPrimaryGroup(player);
		if (group == null) {
			this.helloPlayer(player);
			return true;
		}

		this.helloPlayer(player, group);
		return true;
	}

	private void helloConsole(CommandSender console) {
		console.sendMessage("Hello, console!");
	}

	private void helloPlayer(Player player) {
		player.sendMessage(String.format("Hello, %s!", player.getDisplayName()));
	}

	private void helloPlayer(Player player, String group) {
		player.sendMessage(String.format("Hello, %s! How is everybody in %s?", player.getDisplayName(), group));
	}

}
