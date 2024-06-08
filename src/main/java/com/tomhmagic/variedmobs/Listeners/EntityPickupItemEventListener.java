package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.VariedMobs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class EntityPickupItemEventListener implements Listener {
    @EventHandler
    public void onEntityPickupItem(final EntityPickupItemEvent event) {
        if (event.isCancelled()) return;
        if (event.getEntity() instanceof Player) return;

        LivingEntity entity = event.getEntity();

        int itemHash = event.getItem().getItemStack().hashCode();
        entity.setMetadata("PickedUpItem", new FixedMetadataValue(VariedMobs.getPlugin(), itemHash));

        VariedMobs.debug("Set EntityPickupItem to " + itemHash);
    }
}
