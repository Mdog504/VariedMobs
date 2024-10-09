package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.Utils.EntityUtils;
import com.tomhmagic.variedmobs.Utils.canEntityScale;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

// Scales entities when a chunk is generated
public class ChunkLoadEventListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) return;

        Entity[] entities = event.getChunk().getEntities();

        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity) entity;
            EntityType entityType = livingEntity.getType();
            World entityWorld = livingEntity.getWorld();

            if (!canEntityScale.isAllowed(entityType, entityWorld)) return;

            EntityUtils.setRandomScale(livingEntity);

        }

    }
}
