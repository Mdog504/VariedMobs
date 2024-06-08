package com.tomhmagic.variedmobs.Listeners;

import com.tomhmagic.variedmobs.EntityDenyList;
import com.tomhmagic.variedmobs.Utils.EntityUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

import java.util.Objects;

public class EntityTransformEventListener implements Listener {
    @EventHandler
    public void onEntityTransform(EntityTransformEvent event) {
        if(event.isCancelled()) return;
        if (event.getEntity() instanceof Player) return;
        if (EntityDenyList.isEntityInDenyList((LivingEntity) event.getEntity())) {return;}

        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity oldEntity = (LivingEntity) event.getTransformedEntity();
        LivingEntity entity = (LivingEntity) event.getEntity();

        double scale = Objects.requireNonNull(oldEntity.getAttribute(Attribute.GENERIC_SCALE)).getValue();

        EntityUtils.setScale(entity, scale);
    }
}
