package com.blamejared.botanypotstweaker.recipe.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.BasicCrop;
import net.darkhax.botanypots.data.recipes.crop.Crop;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @docParam this <recipetype:botanypots:crop>
 */
@ZenRegister
@ZenCodeType.Name("mods.botanypots.CropManager")
@Document("mods/BotanyPotsTweaker/CropManager")
public class CropManager implements IRecipeManager<Crop> {
    
    /**
     * Adds a crop.
     *
     * @param id             The ID of the crop.
     * @param seed           The seed ingredient.
     * @param soilCategories The list of soil categories.
     * @param growthTicks    The number of growth ticks.
     * @param results        The array of harvest entries.
     * @param displayState   The display state for the crop.
     * @param lightLevel     The optional light level.
     *
     * @docParam id "crop_test"
     * @docParam seed <item:minecraft:diamond>
     * @docParam soilCategories ["category1"]
     * @docParam growthTicks 200
     * @docParam results [HarvestResult.of(0.75, <item:minecraft:apple>)]
     * @docParam displayState <blockstate:minecraft:dirt>
     * @docParam lightLevel 1
     */
    @ZenCodeType.Method
    public void addCrop(String id, IIngredient seed, List<String> soilCategories, int growthTicks, HarvestEntry[] results, DisplayState displayState, @ZenCodeType.OptionalInt int lightLevel) {
        
        ResourceLocation rl = CraftTweakerConstants.rl(fixRecipeName(id));
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new BasicCrop(rl, seed.asVanillaIngredient(), new HashSet<>(soilCategories), growthTicks, Arrays.asList(results), List.of(displayState), lightLevel)));
    }
    
    /**
     * Adds a crop.
     *
     * @param id             The ID of the crop.
     * @param seed           The seed ingredient.
     * @param soilCategories The list of soil categories.
     * @param growthTicks    The number of growth ticks.
     * @param results        The array of harvest entries.
     * @param displayStates  The array of display states for the crop.
     * @param lightLevel     The optional light level.
     *
     * @docParam id "soil_test"
     * @docParam seed <item:minecraft:diamond>
     * @docParam soilCategories ["category1"]
     * @docParam growthTicks 200
     * @docParam results [HarvestResult.of(0.75, <item:minecraft:apple>)]
     * @docParam displayStates [<blockstate:minecraft:dirt>]
     * @docParam lightLevel 1
     */
    @ZenCodeType.Method
    public void addCrop(String id, IIngredient seed, List<String> soilCategories, int growthTicks, HarvestEntry[] results, DisplayState[] displayStates, @ZenCodeType.OptionalInt int lightLevel) {
        
        ResourceLocation rl = CraftTweakerConstants.rl(fixRecipeName(id));
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new BasicCrop(rl, seed.asVanillaIngredient(), new HashSet<>(soilCategories), growthTicks, Arrays.asList(results), Arrays.asList(displayStates), lightLevel)));
    }
    
    @Override
    public void remove(IIngredient output) {
        
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(this, crop -> {
            if(crop instanceof BasicCrop bc) {
                return bc.getResults()
                        .stream()
                        .anyMatch(harvestEntry -> output.matches(IItemStack.of(harvestEntry.getItem())));
            }
            return false;
        }));
    }
    
    @Override
    public void removeByInput(IItemStack input) {
        
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(this, crop -> {
            if(crop instanceof BasicCrop bc) {
                return bc.getSeed().test(input.getInternal());
            }
            return false;
        }));
    }
    
    @Override
    public List<Crop> getRecipesByOutput(IIngredient output) {
        
        return getRecipeList().getRecipes().values()
                .stream()
                .filter(crop -> {
                    if(crop instanceof BasicCrop bc) {
                        return bc.getResults()
                                .stream()
                                .anyMatch(harvestEntry -> output.matches(IItemStack.of(harvestEntry.getItem())));
                    }
                    return true;
                })
                .toList();
    }
    
    @Override
    public RecipeType<Crop> getRecipeType() {
        
        return BotanyPotHelper.CROP_TYPE.get();
    }
    
}
