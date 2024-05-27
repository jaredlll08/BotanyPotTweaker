package com.blamejared.botanypotstweaker.recipe.handler;

import com.blamejared.botanypotstweaker.recipe.replacement.BPTweakerRecipeComponents;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.BasicCrop;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@IRecipeHandler.For(BasicCrop.class)
public class BasicCropHandler implements IRecipeHandler<BasicCrop> {
    
    @Override
    public String dumpToCommandString(IRecipeManager<? super BasicCrop> manager, BasicCrop recipe) {
        
        return "<recipetype:botanypots:crop>.addCrop(%s, %s, [%s], %s, [%s], [%s], %s);".formatted(
                StringUtil.quoteAndEscape(recipe.getId()),
                IIngredient.fromIngredient(recipe.getSeed()).getCommandString(),
                recipe.getSoilCategories().stream().map(StringUtil::quoteAndEscape).collect(Collectors.joining(", ")),
                recipe.getGrowthTicks(),
                recipe.getResults()
                        .stream()
                        .map(harvestEntry -> "HarvestEntry.of(%s, %s, %s, %s)".formatted(harvestEntry.getChance(), ItemStackUtil.getCommandString(harvestEntry.getItem()), harvestEntry.getMinRolls(), harvestEntry.getMaxRolls()))
                        .collect(Collectors.joining(", ")),
                recipe.getDisplayStates()
                        .stream()
                        .map(DisplayState.SERIALIZER::toJSONString)
                        .map("DisplayState.of(%s)"::formatted)
                        .collect(Collectors.joining(", ")),
                recipe.getLightLevel());
    }
    
    @Override
    public <U extends Recipe<?>> boolean doesConflict(IRecipeManager<? super BasicCrop> manager, BasicCrop firstRecipe, U secondRecipe) {
        
        if(secondRecipe instanceof BasicCrop bc) {
            return IngredientUtil.canConflict(firstRecipe.getSeed(), bc.getSeed());
        }
        return false;
    }
    
    @Override
    public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super BasicCrop> manager, BasicCrop recipe) {
        
        return Optional.of(IDecomposedRecipe.builder()
                .with(BuiltinRecipeComponents.Input.INGREDIENTS, IIngredient.fromIngredient(recipe.getSeed()))
                .with(BPTweakerRecipeComponents.Metadata.SOIL_CATEGORY, new ArrayList<>(recipe.getSoilCategories()))
                .with(BuiltinRecipeComponents.Processing.TIME, recipe.getGrowthTicks())
                .with(BPTweakerRecipeComponents.Output.HARVEST_ENTRIES, recipe.getResults())
                .with(BPTweakerRecipeComponents.Metadata.DISPLAY_STATES, recipe.getDisplayStates())
                .with(BPTweakerRecipeComponents.Metadata.LIGHT_LEVEL, recipe.getLightLevel())
                .build()
        );
    }
    
    @Override
    public Optional<BasicCrop> recompose(IRecipeManager<? super BasicCrop> manager, ResourceLocation name, IDecomposedRecipe recipe) {
        
        Ingredient seed = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS).asVanillaIngredient();
        Set<String> soilCategories = new HashSet<>(recipe.getOrThrow(BPTweakerRecipeComponents.Metadata.SOIL_CATEGORY));
        int growthTicks = recipe.getOrThrowSingle(BuiltinRecipeComponents.Processing.TIME);
        List<HarvestEntry> result = recipe.getOrThrow(BPTweakerRecipeComponents.Output.HARVEST_ENTRIES);
        List<DisplayState> displayStates = recipe.getOrThrow(BPTweakerRecipeComponents.Metadata.DISPLAY_STATES);
        int lightLevel = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Metadata.LIGHT_LEVEL);
        return Optional.of(new BasicCrop(name, seed, soilCategories, growthTicks, result, displayStates, lightLevel));
    }
    
}
