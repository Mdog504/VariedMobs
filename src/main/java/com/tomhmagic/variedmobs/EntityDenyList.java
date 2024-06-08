package com.tomhmagic.variedmobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityDenyList {

    private static final List<EntityType> denyList = new ArrayList<>(Arrays.asList(
            EntityType.AREA_EFFECT_CLOUD,
            EntityType.BLOCK_DISPLAY,
            EntityType.BOAT,
            EntityType.CHEST_BOAT,
            EntityType.COMMAND_BLOCK_MINECART,
            EntityType.DRAGON_FIREBALL,
            EntityType.EGG,
            EntityType.END_CRYSTAL,
            EntityType.ENDER_PEARL,
            EntityType.EXPERIENCE_BOTTLE,
            EntityType.EXPERIENCE_ORB,
            EntityType.FALLING_BLOCK,
            EntityType.FIREWORK_ROCKET,
            EntityType.FURNACE_MINECART,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.HOPPER_MINECART,
            EntityType.ITEM,
            EntityType.ITEM_DISPLAY,
            EntityType.ITEM_FRAME,
            EntityType.MINECART,
            EntityType.OMINOUS_ITEM_SPAWNER,
            EntityType.SPAWNER_MINECART,
            EntityType.TEXT_DISPLAY,
            EntityType.TNT_MINECART,
            EntityType.TRIDENT,
            EntityType.FISHING_BOBBER,
            EntityType.PAINTING,
            EntityType.CHEST_MINECART,
            EntityType.EYE_OF_ENDER,
            EntityType.FIREBALL,
            EntityType.INTERACTION,
            EntityType.LEASH_KNOT,
            EntityType.LIGHTNING_BOLT,
            EntityType.LLAMA_SPIT,
            EntityType.MARKER,
            EntityType.PLAYER,
            EntityType.POTION,
            EntityType.SHULKER_BULLET,
            EntityType.SMALL_FIREBALL,
            EntityType.SNOWBALL,
            EntityType.SPECTRAL_ARROW,
            EntityType.UNKNOWN,
            EntityType.BREEZE_WIND_CHARGE,
            EntityType.WIND_CHARGE,
            EntityType.WITHER,
            EntityType.ENDER_DRAGON
    ));

    public static void initialize() {
        List<String> blacklist = Settings.getScaleBlacklist();

        for (String string : blacklist) {
            try {
                EntityType entityType = EntityType.valueOf(string);
                denyList.add(entityType);
                VariedMobs.debug("Added to EntityDenyList: " + string);
            } catch (IllegalArgumentException e) {
                Bukkit.getServer().getLogger().warning("Invalid EntityType in blacklist: " + string);
            }
        }
    }

    public static boolean isEntityInDenyList(LivingEntity entity) {
        return denyList.contains(entity.getType());
    }
}