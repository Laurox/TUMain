package ga.tumgaming;

import ga.tumgaming.chat.ChatCommand;
import ga.tumgaming.chat.ChatListener;
import ga.tumgaming.files.FileManager;
import ga.tumgaming.listeners.JoinListener;
import ga.tumgaming.listeners.QuitListener;
import ga.tumgaming.msg.MessageCommand;
import ga.tumgaming.msg.RespondCommand;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class TUMain extends JavaPlugin {

	private static Plugin plugin;
	private static Permission perms = null;

	@Override
	public void onEnable() {
		TUMain.plugin = this;

		// checks if the Plugin folder exists, and creates it if not
		// used by other files -> DO NOT MOVE THIS
		checkPluginFolder();

		// VERY important, contains ALL customizable messages TUMain uses
		FileManager.setupMessageFile();

		registerEvents();

		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("respond").setExecutor(new RespondCommand());


		log("§aSuccessfully loaded TUMain");
	}

	/**
	 * logs a string to the console
	 * @param str logged String
	 */
	public static void log(String str) {
		Logger.getLogger(str);
	}

	/**
	 * registers all events used by TUMain
	 */
	private static void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), plugin);
		pluginManager.registerEvents(new QuitListener(), plugin);
		pluginManager.registerEvents(new ChatListener(), plugin);
		log("§aLoaded Listeners...");
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	/**
	 * checks if the plugin folder exists
	 * creates it if not
	 */
	private static void checkPluginFolder() {
		if(plugin.getDataFolder().exists()) return;
		else plugin.getDataFolder().mkdir();
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	public static Permission getPermissions() {
		return perms;
	}

}
