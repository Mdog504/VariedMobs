package com.tomhmagic.variedmobs;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;


public class Settings {

    private static final FileConfiguration config = VariedMobs.getPlugin(VariedMobs.class).getConfig();

    private static final boolean debugMode = config.getBoolean("debugMode");
    private static final double scaleMin = config.getDouble("scale.min");
    private static final double scaleMax = config.getDouble("scale.max");
    private static final List<String> enabledWorlds = config.getStringList("enabled_worlds");
    private static final List<String> allowedMobs = config.getStringList("enabled_entities");

    public static boolean getDebugMode() {
        return debugMode;
    }

    public static double getScaleMin() {
        return scaleMin;
    }

    public static double getScaleMax() {
        return scaleMax;
    }

    public static List<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    public static List<String> getAllowedMobs() {
        return allowedMobs;
    }

}
