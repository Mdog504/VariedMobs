package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.Utils.canEntityScale;
import com.tomhmagic.variedmobs.Utils.EntityUtils;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


// This class will not work with chunk spawned entities, used to but someone decided to change it. Thanks for that.
public class CreatureSpawnEventListener implements Listener {
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if(event.isCancelled()) return;

        CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();

        if(spawnReason == CreatureSpawnEvent.SpawnReason.BREEDING || spawnReason == CreatureSpawnEvent.SpawnReason.BEEHIVE) return;

        LivingEntity entity = event.getEntity();
        EntityType entityType = entity.getType();
        World entityWorld = entity.getWorld();

        if (!canEntityScale.isAllowed(entityType, entityWorld)) return;

        EntityUtils.setRandomScale(entity);
    }
}
