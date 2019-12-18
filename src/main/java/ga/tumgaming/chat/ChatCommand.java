package ga.tumgaming.chat;

import ga.tumgaming.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatCommand implements CommandExecutor, TabCompleter {

    public static HashMap<Player, String> current = new HashMap<>();
    public static List<Player> team = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 0) {
                player.sendMessage(Chat.PREFIX + "These are all possible chats:");

                String currentChat = current.get(player);
                ChatType[] chatTypes = ChatType.getAll();
                StringBuilder sb = new StringBuilder();

                for (ChatType ct : chatTypes) {
                    if(currentChat.equalsIgnoreCase(ct.getClearName()) && ct.isChooseable()) {
                        sb.append("§e").append(ct.getClearName()).append(" ");
                    } else if(ct.isChooseable() && !ct.getClearName().equalsIgnoreCase("Team")) {
                        sb.append("§7").append(ct.getClearName()).append(" ");
                    }
                }

                player.sendMessage(sb.toString());
                return true;
            } else if (args.length == 1) {
                String chat = args[0];
                if(ChatType.matches(chat) && ChatType.getType(chat).isChooseable()) {
                    if(ChatType.getType(chat).equals(ChatType.TEAM) && !player.hasPermission("tum.teamchat")) return false;
                    current.put(player, chat);
                    player.sendMessage(Chat.PREFIX + "you changed to chat " + chat);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    // TODO: TabComplete for /chat
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return null;
    }
}
