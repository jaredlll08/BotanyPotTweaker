package com.blamejared.botanypotstweaker.recipe.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.soil.BasicSoil;
import net.darkhax.botanypots.data.recipes.soil.Soil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @docParam this <recipetype:botanypots:soil>
 */
@ZenRegister
@ZenCodeType.Name("mods.botanypots.SoilManager")
@Document("mods/BotanyPotsTweaker/SoilManager")
public class SoilManager implements IRecipeManager<Soil> {
    
    /**
     * Adds a new soil.
     *
     * @param id             The ID of the soil.
     * @param ingredient     The ingredient that represents the soil.
     * @param displayState    The display state of the soil.
     * @param categories     The categories that the soil belongs to.
     * @param growthModifier The optional growth modifier of the soil. Defaults to 1.
     * @param lightLevel     The optional light level that the soil gives. Defaults to 0.
     *
     * @docParam id "soil_test"
     * @docParam ingredient <item:minecraft:dirt>
     * @docParam displayState SimpleDisplayState.of(<blockstate:minecraft:diamond_block>)
     * @docParam categories ["category1", "category2"]
     * @docParam growthModifier 1.5
     * @docParam lightLevel 7
     */
    @ZenCodeType.Method
    public void addSoil(String id, IIngredient ingredient, DisplayState displayState, List<String> categories, @ZenCodeType.OptionalFloat(1) float growthModifier, @ZenCodeType.OptionalInt int lightLevel) {
        
        ResourceLocation rl = CraftTweakerConstants.rl(fixRecipeName(id));
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new BasicSoil(rl, ingredient.asVanillaIngredient(), displayState, growthModifier, new HashSet<>(categories), lightLevel)));
    }
    
    /**
     * Modifies the given {@link BasicSoil}, replacing it with the new one from the function.
     *
     * @param id       The id of the soil to replace.
     * @param modifier the modifier to apply to the soil.
     *
     * @return true if the soil was found, and is a {@link BasicSoil}, false otherwise.
     *
     * @docParam id "botanypots:minecraft/soil/dirt"
     * @docParam modifier (id, old) => BasicSoil.of(id, old. ingredient, old.displayState, old.categories, old.growthModifier, old.lightLevel)
     */
    @ZenCodeType.Method
    public boolean modify(String id, BiFunction<ResourceLocation, BasicSoil, BasicSoil> modifier) {
        
        Soil soil = getRecipeList().get(id);
        if(soil instanceof BasicSoil bs) {
            ResourceLocation name = new ResourceLocation(id);
            CraftTweakerAPI.apply(new ActionRemoveRecipeByName<>(this, name));
            CraftTweakerAPI.apply(new ActionAddRecipe<>(this, modifier.apply(name, bs)));
            return true;
        }
        return false;
    }
    
    @Override
    public void remove(IIngredient output) {
        
        throw new UnsupportedOperationException("Unable to remove a soil by its output as it doesn't have one! Use 'removeByInput' instead!");
    }
    
    @Override
    public void removeByInput(IItemStack input) {
        
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(this, iRecipe -> {
            if(iRecipe instanceof BasicSoil bs) {
                return bs.getIngredient().test(input.getInternal());
            }
            return false;
        }));
    }
    
    @Override
    public RecipeType<Soil> getRecipeType() {
        
        return BotanyPotHelper.SOIL_TYPE.get();
    }
    
}
