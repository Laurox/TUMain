package ga.tumgaming.listeners;

import ga.tumgaming.chat.ChatCommand;
import ga.tumgaming.files.FileManager;
import ga.tumgaming.util.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        List<String> broadcasts = FileManager.getBroadcasts();

        Methods.sendCenteredMessage(player, broadcasts.get(0));
        Methods.sendCenteredMessage(player, "This is a test");

        // Chat Configs
        ChatCommand.current.put(player, "global");

        if(player.isOp() || player.hasPermission("tum.teamchat"))
            ChatCommand.team.add(player);

        // set tablist decoration
        String header = FileManager.getMessage("JoinListener.header");
        String footer = FileManager.getMessage("JoinListener.footer");
        player.setPlayerListHeaderFooter(header, footer);

        // set tablist name
        // String primaryGroup = TUMain.getPermissions().getPrimaryGroup(player);
        String primaryGroup = "GROUP";
        player.setPlayerListName(FileManager.getMessage("JoinListener.prefix")
                .replace("%player%", player.getDisplayName())
                .replace("%prefix%", primaryGroup)
        );

        // broadcast message
        event.setJoinMessage(FileManager.getMessage("JoinListener.broadcast")
                .replace("%player%", player.getDisplayName())
        );

    }

}
