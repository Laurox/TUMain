package ga.tumgaming.chat;

import ga.tumgaming.files.FileManager;
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
                String prompt = FileManager.getMessage("Chat.command.list");

                String currentChat = current.get(player);
                ChatType[] chatTypes = ChatType.getAll();
                StringBuilder sb = new StringBuilder(prompt);

                for (ChatType ct : chatTypes) {
                    if(currentChat.equalsIgnoreCase(ct.getClearName()) && ct.isChooseable()) {
                        sb.append("§e").append(ct.getClearName()).append(" ");
                    } else if(ct.isChooseable()) {
                        sb.append("§7").append(ct.getClearName()).append(" ");
                    }
                }

                player.sendMessage(sb.toString());
                return true;
            } else if (args.length == 1) {
                String chat = args[0];
                if(ChatType.matches(chat) && ChatType.getType(chat).isChooseable()) {
                    if(ChatType.getType(chat).equals(ChatType.TEAM) && !player.hasPermission("tum.teamchat")) {
                        player.sendMessage("Im sorry! You are not permitted to do that.");
                        return false;
                    }
                    current.put(player, chat);
                    player.sendMessage(FileManager.getMessage("Chat.command.switch")
                            .replace("%chat%", chat)
                    );
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
