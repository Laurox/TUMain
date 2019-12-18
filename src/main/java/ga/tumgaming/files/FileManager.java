package ga.tumgaming.files;

import ga.tumgaming.TUMain;
import ga.tumgaming.util.Chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileManager {
    private static File messageFile;
    private static FileConfiguration messages;

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

    public static String getMessage(String key) {
        // todo translate Colors
        return Chat.translateAlternatives(messages.getString(key));
    }

}
