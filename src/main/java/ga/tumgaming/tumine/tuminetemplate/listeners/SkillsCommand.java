package ga.tumgaming.tumine.tuminetemplate.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class SkillsCommand implements CommandExecutor {

	public SkillsCommand() {
	}

	@EventHandler
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (commandSender instanceof Player) {
			Player sender = (Player) commandSender;
			switch (args[0]) {
			case "info": {
				// skills info
				break;
			}
			default: {
				// skills help
				sender.sendMessage("The TUMineMail Plugin has the following commands:\n"
						+ "/XX\n"
						+ "/XX\n"
						+ "/XX");
				break;
			}
			}
		}
		return false;
	}

}
