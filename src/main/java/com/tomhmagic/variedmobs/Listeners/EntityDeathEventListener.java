package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.Utils.EntityUtils;
import com.tomhmagic.variedmobs.Utils.canEntityScale;
import com.tomhmagic.variedmobs.VariedMobs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class EntityDeathEventListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (!canEntityScale.isAllowed(event.getEntity().getType(), event.getEntity().getWorld())) return;

        LivingEntity entity = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        if (drops.isEmpty()) return;

        boolean skipSetDropCount;
        for (ItemStack drop : drops) {
            skipSetDropCount = false;

            if (entity.hasMetadata("PickedUpItem")) {
                List<MetadataValue> metadataValues = entity.getMetadata("PickedUpItem");

                int hashCode = drop.hashCode();


                for (MetadataValue value : metadataValues) {
                    int storedHash = value.asInt();

                    VariedMobs.debug("PickedUpItem: " + storedHash);
                    VariedMobs.debug("dropItem: " + hashCode);

                    if (hashCode == storedHash) {
                        skipSetDropCount = true;
                        break;
                    }
                }
            }

            if (!skipSetDropCount) {
                EntityUtils.setDropCount(drop, EntityUtils.getScale(entity));
            }
        }
    }
}
