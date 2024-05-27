package com.blamejared.botanypotstweaker.expand;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.item.IItemStack")
@Document("mods/BotanyPotsTweaker/expands/IItemStackExpansions")
public class BPTExpandIItemStack {
    
    /**
     * Converts the given IItemStack into a HarvestEntry.
     *
     * @param internal The IItemStack to convert.
     * @return A HarvestEntry object with default values.
     */
    @ZenCodeType.Caster(implicit = true)
    public static HarvestEntry asHarvestEntry(IItemStack internal) {
        
        return new HarvestEntry(1, internal.getInternal(), 1, 1);
    }
    
}
