package ga.tumgaming.msg;

import ga.tumgaming.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RespondCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(!MessageCommand.messageMap.containsKey(player)) return false;
            if (args.length < 1) return false;
            Player recipient = Bukkit.getPlayer(MessageCommand.messageMap.get(player).getName());
            if(recipient == null) return false;

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(args[i]).append(" ");
            }
            String message = stringBuilder.toString();

            player.sendMessage(FileManager.getMessage("Message.send")
                    .replace("%player%", recipient.getDisplayName())
                    .replace("%message%", message)
            );
            recipient.sendMessage(FileManager.getMessage("Message.receive")
                    .replace("%player%", player.getDisplayName())
                    .replace("%message%", message)
            );

            MessageCommand.messageMap.put(recipient, player);
            return true;
        }
        return false;
    }

}
