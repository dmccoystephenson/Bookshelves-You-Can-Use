package dmccoystephenson.bookshelvesyoucanuse.services;

/*
    To add a new config option, the following methods must be altered:
    - saveMissingConfigDefaultsIfNotPresent
    - setConfigOption()
    - sendConfigList()
 */

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import dmccoystephenson.bookshelvesyoucanuse.BookshelvesYouCanUse;

/**
 * @author Daniel McCoy Stephenson
 * @since August 28th, 2022
 */
public class ConfigService {
    private final BookshelvesYouCanUse plugin;
    private boolean altered = false;

    public ConfigService(BookshelvesYouCanUse plugin) {
        this.plugin = plugin;
    }

    public void saveMissingConfigDefaultsIfNotPresent() {
        // set version
        if (!getConfig().isString("version")) {
            getConfig().addDefault("version", plugin.getVersion());
        } else {
            getConfig().set("version", plugin.getVersion());
        }

        // save config options
        if (!isSet("debugMode")) { getConfig().set("debugMode", false); }

        getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }

    public void setConfigOption(String option, String value, CommandSender sender) {
        if (getConfig().isSet(option)) {
            if (option.equalsIgnoreCase("version")) {
                sender.sendMessage(ChatColor.RED + "Cannot set version.");
                return;
            } else if (option.equalsIgnoreCase("A")) {
                getConfig().set(option, Integer.parseInt(value));
                sender.sendMessage(ChatColor.GREEN + "Integer set.");
            } else if (option.equalsIgnoreCase("debugMode")) {
                getConfig().set(option, Boolean.parseBoolean(value));
                sender.sendMessage(ChatColor.GREEN + "Boolean set.");
            } else if (option.equalsIgnoreCase("C")) {
                getConfig().set(option, Double.parseDouble(value));
                sender.sendMessage(ChatColor.GREEN + "Double set.");
            } else {
                getConfig().set(option, value);
                sender.sendMessage(ChatColor.GREEN + "String set.");
            }

            // save
            plugin.saveConfig();
            altered = true;
        } else {
            sender.sendMessage(ChatColor.RED + "That config option wasn't found.");
        }
    }

    public void sendConfigList(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "=== Config List ===");
        sender.sendMessage(ChatColor.AQUA + "version: " + getConfig().getString("version")
                + ", debugMode: " + getString("debugMode"));
    }

    public boolean hasBeenAltered() {
        return altered;
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public boolean isSet(String option) {
        return getConfig().isSet(option);
    }

    public int getInt(String option) {
        return getConfig().getInt(option);
    }

    public int getIntOrDefault(String option, int defaultValue) {
        int toReturn = getInt(option);
        if (toReturn == 0) {
            return defaultValue;
        }
        return toReturn;
    }

    public boolean getBoolean(String option) {
        return getConfig().getBoolean(option);
    }

    public double getDouble(String option) {
        return getConfig().getDouble(option);
    }

    public double getDoubleOrDefault(String option, double defaultValue) {
        double toReturn = getDouble(option);
        if (toReturn == 0) {
            return defaultValue;
        }
        return toReturn;
    }

    public String getString(String option) {
        return getConfig().getString(option);
    }

    public String getStringOrDefault(String option, String defaultValue) {
        String toReturn = getString(option);
        if (toReturn == null) {
            return defaultValue;
        }
        return toReturn;
    }
}