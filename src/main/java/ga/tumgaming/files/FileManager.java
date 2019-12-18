package ga.tumgaming.files;

import ga.tumgaming.TUMain;
import ga.tumgaming.util.Chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FileManager {
    private static File messageFile;
    private static FileConfiguration messages;

    private static File broadcastsText;

    public static void setupMessageFile() {
        messageFile = new File(TUMain.getPlugin().getDataFolder(), "messages.yml");

        if(messageFile.exists())
            messageFile.delete();

        if (!messageFile.exists()) {
            try {
                InputStream stream = FileManager.class.getResourceAsStream("messages.yml");
                Files.copy(stream, messageFile.toPath());
            } catch (IOException exception) {
                TUMain.log("Cant find or copy messages.yml to Plugin Folder");
                TUMain.log("Unloading Plugin...");
                exception.printStackTrace();
                TUMain.getPlugin().getPluginLoader().disablePlugin(TUMain.getPlugin());
            }
        }

        messages = YamlConfiguration.loadConfiguration(messageFile);
    }

    public static void loadBroadcasts() {
        broadcastsText = new File(TUMain.getPlugin().getDataFolder(), "messages.yml");

        if(broadcastsText.exists())
            broadcastsText.delete();

        if (!broadcastsText.exists()) {
            try {
                InputStream stream = FileManager.class.getResourceAsStream("broadcasts.txt");
                Files.copy(stream, broadcastsText.toPath());
            } catch (IOException exception) {
                TUMain.log("Cant find or copy File to Plugin Folder");
                TUMain.log("Unloading Plugin...");
                exception.printStackTrace();
                TUMain.getPlugin().getPluginLoader().disablePlugin(TUMain.getPlugin());
            }
        }
    }

    public static List<String> getBroadcasts() {
        List<String> result = new ArrayList<>();
        String broadcast = "";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(broadcastsText.toPath())) {

            Iterator<String> iterator = stream.iterator();

            while (iterator.hasNext()) {
                String line = iterator.next();

                // Comments start with #
                if(!line.startsWith("#")) {
                    if(line.isEmpty());
                    else {
                        if(line.startsWith("// START //")) broadcast = "";
                        else if(line.startsWith("// END //")) {
                            result.add(broadcast);
                        } else {
                            broadcast += iterator.next() + "\n";
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getMessage(String key) {
        // todo translate Colors
        return Chat.translateAlternatives(messages.getString(key));
    }

}
