package com.tomhmagic.variedmobs;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Settings {

    private static final FileConfiguration config = VariedMobs.getPlugin(VariedMobs.class).getConfig();

    private static final boolean debugMode = config.getBoolean("main.debugMode");
    private static final double scaleCapMin = config.getDouble("main.scaleCaps.min");
    private static final double scaleCapMax = config.getDouble("main.scaleCaps.max");

    public static boolean getDebugMode() {
        return debugMode;
    }

    public static double getScaleCapMin() {
        return scaleCapMin;
    }
    public static double getScaleCapMax() {
        return scaleCapMax;
    }

    public static List<String> getScaleBlacklist() {
        return config.getStringList("scale_blacklist");
    }
}
