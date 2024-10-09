package com.tomhmagic.variedmobs.Utils;

import com.tomhmagic.variedmobs.Settings;
import com.tomhmagic.variedmobs.VariedMobs;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class EntityUtils {
    public static double getScale(LivingEntity entity) {
        // Get the scale of the entity
        return Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_SCALE)).getBaseValue();
    }

    public static void setScale(LivingEntity entity, double scale) {
        // This will change the size, health, and damage dealt by the entity
        if (!isSafeToScale(entity)) return;

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(scale);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() * scale);
        entity.setHealth(Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());

        // Ensure the entity can actually have attack damage
        if (entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
            Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue() * scale);
        }

        // Debug message
        VariedMobs.debug("Setting scale to " + scale + " for " + entity.getName());
    }

    public static void setRandomScale(LivingEntity entity) {
        // Set the scale of the entity to a random value
        if (!isSafeToScale(entity)) return;

        // It is essential to first get the min and max scale values from the config file
        double min = Settings.getScaleMin();
        double max = Settings.getScaleMax();

        double scale = Math.random() * (max - min) + min;

        VariedMobs.debug("Random scale value: " + scale);

        setScale(entity, scale);
    }

    public static void setParentalAverageScale(LivingEntity father, LivingEntity mother, LivingEntity child) {
        // Set the scale of the child entity to the average of the parent entities
        if (!isSafeToScale(child)) return;

        double fatherScale = Objects.requireNonNull(father.getAttribute(Attribute.GENERIC_SCALE)).getBaseValue();
        double motherScale = Objects.requireNonNull(mother.getAttribute(Attribute.GENERIC_SCALE)).getBaseValue();

        double average = (fatherScale + motherScale) / 2.0;

        VariedMobs.debug("Breed, father is " + fatherScale + " mother is " + motherScale + " average is " + average);

        setScale(child, average);
    }

    public static void setDropCount(ItemStack item, double scale) {
        // Set the drop count of the item based on the scale of the entity
        int amount = item.getAmount();

        if (scale < 0.9) {
            amount = amount - 2;
        } else if (scale > 0.9 && scale < 1.0) {
            amount--;
        } else if (scale > 1.0 && scale <= 1.1) {
            amount++;
        } else if (scale > 1.1 && scale <= 1.2) {
            amount = amount + 2;
        } else if (scale > 1.2) {
            amount = amount + 3;
        }

        if (amount < 0) {
            amount = 0;
        }

        item.setAmount(amount);

        VariedMobs.debug("Setting drop count to " + amount + " for " + Objects.requireNonNull(item.getType()));
    }


    // Whilst this could be inverted, I prefer it as it is for readability
    public static boolean isSafeToScale(LivingEntity entity) {
        // Check if it is safe to scale the entity to prevent any shenanigans from happening

        Location location = entity.getLocation();
        VariedMobs.debug("Location is " + location);
        double height = entity.getBoundingBox().getHeight();
        VariedMobs.debug("Height is " + height);
        Location topLoc = location.clone().add(0, Math.ceil(height), 0);
        VariedMobs.debug("Top location is " + topLoc);

        if(height < 1.0){
            return false;
        }

        return !topLoc.getBlock().getType().isSolid();
    }
}
