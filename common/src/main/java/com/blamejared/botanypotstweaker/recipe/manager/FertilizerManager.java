package com.blamejared.botanypotstweaker.recipe.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.data.recipes.fertilizer.BasicFertilizer;
import net.darkhax.botanypots.data.recipes.fertilizer.Fertilizer;
import net.minecraft.Optionull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.BiFunction;

/**
 * @docParam this <recipetype:botanypots:fertilizer>
 */
@ZenRegister
@ZenCodeType.Name("mods.botanypots.FertilizerManager")
@Document("mods/BotanyPotsTweaker/FertilizerManager")
public class FertilizerManager implements IRecipeManager<Fertilizer> {
    
    /**
     * Adds a fertilizer.
     *
     * @param id             The ID of the fertilizer.
     * @param ingredient     The item that triggers the fertilization.
     * @param minTicks       The minimum amount of ticks that the fertilizer applies.
     * @param maxTicks       The maximum amount of ticks that the fertilizer applies.
     * @param cropIngredient The ingredient for the crop that the fertilizer affects.
     * @param soilIngredient The ingredient for the soil that the fertilizer affects.
     *
     * @docParam id "fertilizer_test"
     * @docParam ingredient <item:minecraft:diamond>
     * @docParam minTicks 20
     * @docParam maxTicks 40
     * @docParam cropIngredient <item:minecraft:carrot>
     * @docParam soilIngredient <item:minecraft:dirt>
     */
    @ZenCodeType.Method
    public void addFertilizer(String id, IIngredient ingredient, int minTicks, int maxTicks, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient cropIngredient, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient soilIngredient) {
        
        ResourceLocation rl = CraftTweakerConstants.rl(fixRecipeName(id));
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new BasicFertilizer(rl, ingredient.asVanillaIngredient(), Optionull.map(cropIngredient, IIngredient::asVanillaIngredient), Optionull.map(soilIngredient, IIngredient::asVanillaIngredient), minTicks, maxTicks)));
    }
    
    /**
     * Modifies the given {@link BasicFertilizer}, replacing it with the new one from the function.
     *
     * @param id       The id of the fertilizer to replace.
     * @param modifier the modifier to apply to the fertilizer.
     *
     * @return true if the fertilizer was found, and is a {@link BasicFertilizer}, false otherwise.
     *
     * @docParam id "botanypots:minecraft/fertilizer/bonemeal"
     * @docParam modifier (id, old) => BasicFertilizer.of(id, old.ingredient, old.minTicks, old.maxTicks * 10, old.cropIngredient, old.soilIngredient)
     */
    @ZenCodeType.Method
    public boolean modify(String id, BiFunction<ResourceLocation, BasicFertilizer, BasicFertilizer> modifier) {
        
        Fertilizer fertilizer = getRecipeList().get(id);
        if(fertilizer instanceof BasicFertilizer bf) {
            ResourceLocation name = new ResourceLocation(id);
            CraftTweakerAPI.apply(new ActionRemoveRecipeByName<>(this, name));
            CraftTweakerAPI.apply(new ActionAddRecipe<>(this, modifier.apply(name, bf)));
            return true;
        }
        return false;
    }
    
    @Override
    public void remove(IIngredient output) {
        
        throw new UnsupportedOperationException("Unable to remove a Fertilizer by its output!");
    }
    
    @Override
    public void removeByInput(IItemStack input) {
        
        throw new UnsupportedOperationException("Unable to remove a Fertilizer by its input!");
    }
    
    @Override
    public RecipeType<Fertilizer> getRecipeType() {
        
        return BotanyPotHelper.FERTILIZER_TYPE.get();
    }
    
}
