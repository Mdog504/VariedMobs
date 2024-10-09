package com.tomhmagic.variedmobs;

import com.tomhmagic.variedmobs.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VariedMobs extends JavaPlugin {

    private static VariedMobs plugin;

    // The below is unused, but commented out in case it is needed in future development
//    public static final String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "VariedMobs" + ChatColor.WHITE + "]";

    public VariedMobs() {
        plugin = this;
    }
    public static VariedMobs getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        registerListeners();
        printArt();
    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawnEventListener(), this);
        pm.registerEvents(new EntityBreedEventListener(), this);
        pm.registerEvents(new EntityDeathEventListener(), this);
        pm.registerEvents(new EntityTransformEventListener(), this);
        pm.registerEvents(new EntityPickupItemEventListener(), this);
        pm.registerEvents(new ChunkLoadEventListener(), this);
    }

    private void printArt() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "            ----- VARIED MOBS by Tomhmagic -----");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "              https://discord.gg/7eUecQ3sBT");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static void debug(String msg){
        if(Settings.getDebugMode()){
            // Better to use the logger for debugging else the server complains
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[DEBUG] " + ChatColor.GRAY + msg);
        }
    }
}
