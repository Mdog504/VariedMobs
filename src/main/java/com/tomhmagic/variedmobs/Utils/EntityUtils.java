package com.tomhmagic.variedmobs.Utils;

import com.tomhmagic.variedmobs.Settings;
import com.tomhmagic.variedmobs.VariedMobs;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

public class EntityUtils {

    public static void setScale(LivingEntity entity, double scale){

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(scale);

        VariedMobs.debug("Setting scale to " + scale + " for " + entity.getName());
    }
    public static void setRandomScale(LivingEntity entity){
        if(isSafeToScaleUp(entity)){
            setScale(entity, getRandomScaleValue(null, null));
        } else {
            setScale(entity, getRandomScaleValue(null, 1.0));
        }
    }

    public static void setDropCount(ItemStack item, double scale){
        int amount = getDropAmount(item, scale);
        item.setAmount(amount);

        VariedMobs.debug("Setting drop count to " + amount + " for " + Objects.requireNonNull(item.getType()));
    }

    public static double getScale(LivingEntity entity){
        return Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_SCALE)).getValue();
    }

    public static double getParentAverageScale(LivingEntity father, LivingEntity mother){
        double fatherScale = getScale(father);
        double motherScale = getScale(mother);

        double average = (fatherScale + motherScale) / 2.0;

        VariedMobs.debug("Breed, father is " + fatherScale + " mother is " + motherScale + " average is " + average);
        return average;
    }

        private static final DecimalFormat df = new DecimalFormat("#.##");

    private static double getRandomScaleValue(@Nullable Double min, @Nullable Double max) {
        Random random = new Random();

        if (min == null) {
            min = Settings.getScaleCapMin();
        }

        if (max == null) {
            max = Settings.getScaleCapMax();
        }
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }
        double scaledValue = min + random.nextDouble() * (max - min);
        return Double.parseDouble(df.format(scaledValue));
        }

    private static int getDropAmount(ItemStack item, double scale){
        int amount = item.getAmount();

        if(scale < 0.9){
            amount = amount - 2;
        } else if (scale > 0.9 && scale < 1.0){
            amount--;
        } else if (scale > 1.0 && scale <= 1.1){
            amount++;
        } else if (scale > 1.1 && scale <= 1.2){
            amount = amount + 2;
        }else if (scale > 1.2){
            amount = amount + 3;
        }


        if(amount < 0){
            amount = 0;
        }

        VariedMobs.debug("Getting drop amount " + item.getAmount() + " for " + Objects.requireNonNull(item.getType()) + " round is " + amount + " from " + scale + " scale");

        return amount;
    }

    private static boolean isSafeToScaleUp(LivingEntity entity){
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
