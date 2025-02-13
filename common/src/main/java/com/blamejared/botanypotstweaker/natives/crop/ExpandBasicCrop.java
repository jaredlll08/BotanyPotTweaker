package com.blamejared.botanypotstweaker.natives.crop;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.BasicCrop;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * A basic definition of a crop.
 */
@ZenRegister
@Document("mods/BotanyPotsTweaker/crop/BasicCrop")
@NativeTypeRegistration(value = BasicCrop.class, zenCodeName = "mods.botanypotstweaker.crop.BasicCrop")
public class ExpandBasicCrop {
    
    /**
     * Creates a new BasicCrop
     *
     * @param id             The ID of the crop.
     * @param seed           The seed ingredient.
     * @param soilCategories The list of soil categories.
     * @param growthTicks    The number of growth ticks.
     * @param results        The array of harvest entries.
     * @param displayStates  The array of display states for the crop.
     * @param lightLevel     The optional light level that the crop will give off. Defaults to 0
     *
     * @docParam id <resource:crafttweaker:soil_test>
     * @docParam seed <item:minecraft:diamond>
     * @docParam soilCategories ["category1"]
     * @docParam growthTicks 200
     * @docParam results [HarvestResult.of(0.75, <item:minecraft:apple>)]
     * @docParam displayStates [<blockstate:minecraft:dirt>]
     * @docParam lightLevel 1
     */
    @ZenCodeType.StaticExpansionMethod
    public static BasicCrop of(ResourceLocation id, IIngredient seed, List<String> soilCategories, int growthTicks, HarvestEntry[] results, DisplayState[] displayStates, @ZenCodeType.OptionalInt int lightLevel) {
        
        return new BasicCrop(id, seed.asVanillaIngredient(), new HashSet<>(soilCategories), growthTicks, Arrays.asList(results), Arrays.asList(displayStates), lightLevel);
    }
    
    /**
     * Gets the light level of the crop.
     *
     * @return The light level of the crop.
     */
    @ZenCodeType.Getter("lightLevel")
    public static int getLightLevel(BasicCrop internal) {
        
        return internal.getLightLevel();
    }
    
    /**
     * Gets how the display states of the crop
     *
     * @return the display states of the crop.
     */
    @ZenCodeType.Getter("displayStates")
    public static DisplayState[] getDisplayStates(BasicCrop internal) {
        
        return internal.getDisplayStates().toArray(DisplayState[]::new);
    }
    
    /**
     * Gets what this crop will grow.
     *
     * @return The results of this crop.
     */
    @ZenCodeType.Getter("results")
    public static HarvestEntry[] getResults(BasicCrop internal) {
        
        return internal.getResults().toArray(HarvestEntry[]::new);
    }
    
    /**
     * Gets the amount of ticks for the crop to grow.
     *
     * @return the amount of ticks for the crop to grow.
     */
    @ZenCodeType.Getter("growthTicks")
    public static int getGrowthTicks(BasicCrop internal) {
        
        return internal.getGrowthTicks();
    }
    
    /**
     * Gets the soil categories that the crop can grow on.
     *
     * @return the soil categories that the crop can grow on.
     */
    @ZenCodeType.Getter("soilCategories")
    public static List<String> getSoilCategories(BasicCrop internal) {
        
        return new ArrayList<>(internal.getSoilCategories());
    }
    
    /**
     * Gets the ingredient that is used as the seed for the crop.
     *
     * @return the ingredient that is used as the seed for the crop
     */
    @ZenCodeType.Getter("seed")
    public static IIngredient getSeed(BasicCrop internal) {
        
        return IIngredient.fromIngredient(internal.getSeed());
    }
    
}
