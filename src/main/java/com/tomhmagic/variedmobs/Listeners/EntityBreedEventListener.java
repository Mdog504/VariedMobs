package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.EntityDenyList;
import com.tomhmagic.variedmobs.Utils.EntityUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class EntityBreedEventListener implements Listener {

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        if(event.isCancelled()) return;
        if (EntityDenyList.isEntityInDenyList(event.getEntity())) {return;}

        LivingEntity baby = event.getEntity();

        LivingEntity father = event.getFather();
        LivingEntity mother = event.getMother();

        double scale = EntityUtils.getParentAverageScale(father, mother);

        EntityUtils.setScale(baby, scale);
    }
}