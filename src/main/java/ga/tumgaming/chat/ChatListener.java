package ga.tumgaming.chat;

import ga.tumgaming.TUMain;
import ga.tumgaming.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);

        String currentChat = ChatCommand.current.get(player);
        ChatType type = ChatType.getType(currentChat);
        assert type != null;

        switch (type) {
            case GLOBAL:
                Bukkit.getOnlinePlayers().forEach((Player) -> Player.sendMessage(FileManager.getMessage("Chat.listener.global")
                        .replace("%player%", player.getDisplayName())
                        .replace("%message%", message)
                ));
                break;
            case TEAM:
                ChatCommand.team.forEach((Player) -> Player.sendMessage(FileManager.getMessage("Chat.listener.team")
                        .replace("%player%", player.getDisplayName())
                        .replace("%message%", message)
                ));
                break;
            case RACE:
                player.sendMessage("Dieser Chat wurde noch nicht konfiguriert");
                break;
            case LOCAL:

                player.sendMessage(FileManager.getMessage("Chat.listener.local")
                        .replace("%player%", player.getDisplayName())
                        .replace("%message%", message)
                );

                AtomicReference<List<Entity>> nearby = new AtomicReference<List<Entity>>();
                Bukkit.getScheduler().callSyncMethod(TUMain.getPlugin(), () -> {
                    nearby.set(player.getNearbyEntities(25, 25, 25));
                    nearby.get().forEach((entity) -> {
                        if (entity instanceof Player) {
                            entity.sendMessage(FileManager.getMessage("Chat.listener.local")
                                    .replace("%player%", player.getDisplayName())
                                    .replace("%message%", message)
                            );
                        }
                    });
                    return null;
                });
                break;
        }

    }

}
