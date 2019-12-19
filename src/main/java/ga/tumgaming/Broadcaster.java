package ga.tumgaming;

import ga.tumgaming.files.FileManager;
import ga.tumgaming.util.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Broadcaster {

    private static int intervall = 10;
    private static boolean sound = true;

    private static String[] broadcasts;

    public Broadcaster()  {
        Object[] objectArray = FileManager.getBroadcasts().toArray();
        broadcasts = Arrays.copyOf(objectArray, objectArray.length, String[].class);
    }

    public static void startScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(TUMain.getPlugin(), new Runnable() {
            @Override
            public void run() {

                int rnd = (int) Math.round(Math.random()*(broadcasts.length-1));
                String[] strings = broadcasts[rnd].split("\n");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if(sound)
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, (float) 0.2, (float) 0.5);

                    player.sendMessage("");
                    for (String s: strings) {
                        Methods.sendCenteredMessage(player, s);
                    }
                    player.sendMessage("");
                }

            }
        }, 0L, intervall*20);
    }
}
