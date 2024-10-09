package com.tomhmagic.variedmobs.Utils;


import com.tomhmagic.variedmobs.Settings;
import com.tomhmagic.variedmobs.VariedMobs;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.List;


// Whilst a deny list works, an allow list would prevent any forgotten or new entities from breaking the plugin
public class canEntityScale {
    // A list of all the entities that are allowed to be varied
    private static final List<EntityType> allowedEntities = Arrays.asList(
            EntityType.ARMADILLO,
            EntityType.BAT,
            EntityType.BEE,
            EntityType.BLAZE,
            EntityType.CAT,
            EntityType.CAVE_SPIDER,
            EntityType.CHICKEN,
            EntityType.COD,
            EntityType.COW,
            EntityType.CREEPER,
            EntityType.DOLPHIN,
            EntityType.DONKEY,
            EntityType.DROWNED,
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDERMAN,
            EntityType.ENDERMITE,
            EntityType.EVOKER,
            EntityType.FOX,
            EntityType.GHAST,
            EntityType.GIANT,
            EntityType.GUARDIAN,
            EntityType.HOGLIN,
            EntityType.HORSE,
            EntityType.HUSK,
            EntityType.ILLUSIONER,
            EntityType.IRON_GOLEM,
            EntityType.LLAMA,
            EntityType.MAGMA_CUBE,
            EntityType.MULE,
            EntityType.MOOSHROOM,
            EntityType.OCELOT,
            EntityType.PANDA,
            EntityType.PARROT,
            EntityType.PHANTOM,
            EntityType.PIG,
            EntityType.PIGLIN,
            EntityType.PIGLIN_BRUTE,
            EntityType.PILLAGER,
            EntityType.POLAR_BEAR,
            EntityType.PUFFERFISH,
            EntityType.RABBIT,
            EntityType.RAVAGER,
            EntityType.SALMON,
            EntityType.SHEEP,
            EntityType.SHULKER,
            EntityType.SILVERFISH,
            EntityType.SKELETON,
            EntityType.SKELETON_HORSE,
            EntityType.SLIME,
            EntityType.SPIDER,
            EntityType.SQUID,
            EntityType.STRAY,
            EntityType.STRIDER,
            EntityType.TRADER_LLAMA,
            EntityType.TROPICAL_FISH,
            EntityType.TURTLE,
            EntityType.VEX,
            EntityType.VILLAGER,
            EntityType.VINDICATOR,
            EntityType.WANDERING_TRADER,
            EntityType.WITCH,
            EntityType.WITHER_SKELETON,
            EntityType.WOLF,
            EntityType.ZOGLIN,
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_HORSE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );

    // Check if the entity is allowed
    // Whilst this could be inverted, I prefer it as it is for readability
    public static boolean isAllowed(EntityType entityType, World world) {
        if (entityType == null) return false;
        VariedMobs.debug("Checking if " + entityType + " is allowed in " + world.getName());

        if (!allowedEntities.contains(entityType)) {
            VariedMobs.debug(entityType + " is not allowed to be varied");
            return false;
        }

        String worldName = world.getName();
        if (!Settings.getEnabledWorlds().contains(worldName)) {
            VariedMobs.debug("World " + worldName + " is not enabled");
            return false;
        }

        List<String> allowedMobs = Settings.getAllowedMobs();
        return allowedMobs.contains(entityType.toString());

    }
}
