package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.EntityDenyList;
import com.tomhmagic.variedmobs.Utils.EntityUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnEventListener implements Listener {




    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if(event.isCancelled()) return;

        CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();

        if(spawnReason == CreatureSpawnEvent.SpawnReason.BREEDING || spawnReason == CreatureSpawnEvent.SpawnReason.BEEHIVE) return;

        LivingEntity entity = event.getEntity();
        if (EntityDenyList.isEntityInDenyList(event.getEntity())) {return;}

        EntityUtils.setRandomScale(entity);
    }
}
