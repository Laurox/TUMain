package ga.tumgaming.msg;

import ga.tumgaming.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class MessageCommand implements CommandExecutor, TabCompleter {

    static HashMap<Player, Player> messageMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 2) return false;
            Player recipient = Bukkit.getPlayer(args[0]);
            if(recipient == null) return false;

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
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

            messageMap.put(recipient, player);
            return true;
        }
        return false;
    }

    // todo
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

}
