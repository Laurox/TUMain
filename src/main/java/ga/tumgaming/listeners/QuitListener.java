package ga.tumgaming.listeners;

import ga.tumgaming.chat.ChatCommand;
import ga.tumgaming.files.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        ChatCommand.current.remove(player);

        event.setQuitMessage(FileManager.getMessage("QuitListener.broadcast")
                .replace("%player%", player.getDisplayName())
        );
    }
}
