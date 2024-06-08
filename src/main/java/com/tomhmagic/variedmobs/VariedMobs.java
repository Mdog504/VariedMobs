package com.tomhmagic.variedmobs;

import com.tomhmagic.variedmobs.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VariedMobs extends JavaPlugin {

    private static VariedMobs plugin;
    private static VariedMobs instance;

    public static final String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "VariedMobs" + ChatColor.WHITE + "]";

    public VariedMobs() {
        plugin = this;
    }
    public static VariedMobs getPlugin() {
        return plugin;
    }

    public String getVersion() {
        return this.getDescription().getVersion();
    }
    public static VariedMobs getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        registerListeners();
        EntityDenyList.initialize();
        printArt();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig(){
        createPluginFolder();
        this.saveDefaultConfig();
    }

    public void createPluginFolder(){
        File f = new File(this.getDataFolder() + "/");
        if(!f.exists())
            f.mkdir();
    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawnEventListener(), this);
        pm.registerEvents(new EntityBreedEventListener(), this);
        pm.registerEvents(new EntityDeathEventListener(), this);
        pm.registerEvents(new EntityTransformEventListener(), this);
        pm.registerEvents(new EntityPickupItemEventListener(), this);
    }

    private void printArt() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "            ----- VARIED MOBS by Tomhmagic -----");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "              https://discord.gg/7eUecQ3sBT");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static void debug(String msg){
        if(Settings.getDebugMode()){
            System.out.println("[DEBUG] " + msg);
        }
    }
}
