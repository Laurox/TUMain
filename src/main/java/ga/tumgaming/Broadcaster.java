package ga.tumgaming;

import ga.tumgaming.files.FileManager;
import ga.tumgaming.util.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Broadcaster {

    private static int intervall = 10;
    private static boolean sound;

    private static String[] broadcasts;

    public Broadcaster()  {
        this.broadcasts = (String[]) FileManager.getBroadcasts().toArray();
    }

    public static void startScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(TUMain.getPlugin(), new Runnable() {
            @Override
            public void run() {

                int rnd = (int) Math.round(Math.random()*(broadcasts.length-1));
                String[] strings = broadcasts[rnd].split("\n");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (String s: strings) {
                        if(sound)
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, (float) 0.2, (float) 0.5);
                        player.sendMessage("");
                        Methods.sendCenteredMessage(player, s);
                        player.sendMessage("");
                    }
                }

            }
        }, 0L, intervall*20);
    }
}
