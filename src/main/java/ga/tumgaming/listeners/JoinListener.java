package ga.tumgaming.listeners;

import ga.tumgaming.TUMain;
import ga.tumgaming.chat.ChatCommand;
import ga.tumgaming.files.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Chat Configs
        ChatCommand.current.put(player, "global");

        if(player.isOp() || player.hasPermission("tum.teamchat"))
            ChatCommand.team.add(player);

        // set tablist decoration
        String header = FileManager.getMessage("JoinListener.header");
        String footer = FileManager.getMessage("JoinListener.footer");
        player.setPlayerListHeaderFooter(header, footer);

        // set tablist name
        String primaryGroup = TUMain.getPermissions().getPrimaryGroup(player);
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
