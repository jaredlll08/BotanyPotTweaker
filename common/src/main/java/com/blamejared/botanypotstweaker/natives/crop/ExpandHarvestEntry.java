package com.blamejared.botanypotstweaker.natives.crop;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/crop/HarvestEntry")
@NativeTypeRegistration(value = HarvestEntry.class, zenCodeName = "mods.botanypotstweaker.crop.HarvestEntry")
public class ExpandHarvestEntry {
    
    @ZenCodeType.StaticExpansionMethod
    public static HarvestEntry of(float chance, IItemStack item, @ZenCodeType.OptionalInt(1) int minRolls, @ZenCodeType.OptionalInt(1) int maxRolls) {
        
        return new HarvestEntry(chance, item.getInternal(), minRolls, maxRolls);
    }
    
    @ZenCodeType.Getter("chance")
    public static float getChance(HarvestEntry internal) {
        
        return internal.getChance();
    }
    
    @ZenCodeType.Getter("minRolls")
    public static int getMinRolls(HarvestEntry internal) {
        
        return internal.getMinRolls();
    }
    
    @ZenCodeType.Getter("maxRolls")
    public static int getMaxRolls(HarvestEntry internal) {
        
        return internal.getMaxRolls();
    }
    
    @ZenCodeType.Getter("item")
    public static IItemStack getItem(HarvestEntry internal) {
        
        return IItemStack.of(internal.getItem());
    }
    
}
