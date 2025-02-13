package com.blamejared.botanypotstweaker.natives.crop;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Represents the output of a crop
 */
@ZenRegister
@Document("mods/BotanyPotsTweaker/crop/HarvestEntry")
@NativeTypeRegistration(value = HarvestEntry.class, zenCodeName = "mods.botanypotstweaker.crop.HarvestEntry")
public class ExpandHarvestEntry {
    
    /**
     * Creates a new HarvestEntry.
     *
     * @param chance   The chance for this entry to happen.
     * @param item     The item to give when harvested.
     * @param minRolls The lowest amount of the item to give.
     * @param maxRolls The maximum amount of the item to give.
     *
     * @return a new harvest entry with the given values.
     */
    @ZenCodeType.StaticExpansionMethod
    public static HarvestEntry of(float chance, IItemStack item, @ZenCodeType.OptionalInt(1) int minRolls, @ZenCodeType.OptionalInt(1) int maxRolls) {
        
        return new HarvestEntry(chance, item.getInternal(), minRolls, maxRolls);
    }
    
    /**
     * Gets the chance for this entry to happen
     *
     * @return the chance for this entry to happen
     */
    @ZenCodeType.Getter("chance")
    public static float getChance(HarvestEntry internal) {
        
        return internal.getChance();
    }
    
    /**
     * Gets the lowest amount of the item to give.
     *
     * @return the lowest amount of the item to give.
     */
    @ZenCodeType.Getter("minRolls")
    public static int getMinRolls(HarvestEntry internal) {
        
        return internal.getMinRolls();
    }
    
    /**
     * Gets the highest amount of the item to give.
     *
     * @return the highest amount of the item to give.
     */
    @ZenCodeType.Getter("maxRolls")
    public static int getMaxRolls(HarvestEntry internal) {
        
        return internal.getMaxRolls();
    }
    
    /**
     * Gets the item to give.
     *
     * @return the item to give.
     */
    @ZenCodeType.Getter("item")
    public static IItemStack getItem(HarvestEntry internal) {
        
        return IItemStack.of(internal.getItem());
    }
    
}
