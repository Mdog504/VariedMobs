package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.Utils.canEntityScale;
import com.tomhmagic.variedmobs.Utils.EntityUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class EntityBreedEventListener implements Listener {

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        if(event.isCancelled()) return;
        if (!canEntityScale.isAllowed(event.getEntity().getType(), event.getEntity().getWorld())) return;

        LivingEntity baby = event.getEntity();

        LivingEntity father = event.getFather();
        LivingEntity mother = event.getMother();

        EntityUtils.setParentalAverageScale(father, mother, baby);
    }
}