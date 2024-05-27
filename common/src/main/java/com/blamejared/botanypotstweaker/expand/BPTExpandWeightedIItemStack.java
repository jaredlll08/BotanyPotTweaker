package com.blamejared.botanypotstweaker.expand;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.util.random.Percentaged;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.util.random.Percentaged<crafttweaker.api.item.IItemStack>")
@Document("mods/BotanyPotsTweaker/expands/PercentagedIItemStack")
public class BPTExpandWeightedIItemStack {
    
    /**
     * Converts a Percentaged<IItemStack> to a HarvestEntry.
     *
     * @param internal the input Percentaged<IItemStack>
     * @return the resulting HarvestEntry
     */
    @ZenCodeType.Caster(implicit = true)
    public static HarvestEntry asHarvestEntry(Percentaged<IItemStack> internal) {
        
        return new HarvestEntry((float) internal.getPercentage(), internal.getData().getInternal(), 1, 1);
    }
    
}
