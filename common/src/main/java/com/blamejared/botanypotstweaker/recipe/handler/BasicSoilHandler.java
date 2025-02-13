package com.blamejared.botanypotstweaker.recipe.handler;

import com.blamejared.botanypotstweaker.recipe.replacement.BPTweakerRecipeComponents;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.soil.BasicSoil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@IRecipeHandler.For(BasicSoil.class)
public class BasicSoilHandler implements IRecipeHandler<BasicSoil> {
    
    @Override
    public String dumpToCommandString(IRecipeManager<? super BasicSoil> manager, BasicSoil recipe) {
        
        return "<recipetype:botanypots:soil>.addSoil(%s, %s, %s, [%s], %s, %s);".formatted(
                StringUtil.quoteAndEscape(recipe.getId()),
                IIngredient.fromIngredient(recipe.getIngredient()).getCommandString(),
                "DisplayState.of(%s)".formatted(DisplayState.SERIALIZER.toJSONString(recipe.getDisplayState())),
                recipe.getCategories().stream().map(StringUtil::quoteAndEscape).collect(Collectors.joining(", ")),
                recipe.getGrowthModifier(),
                recipe.getLightLevel());
    }
    
    @Override
    public <U extends Recipe<?>> boolean doesConflict(IRecipeManager<? super BasicSoil> manager, BasicSoil firstRecipe, U secondRecipe) {
        
        if(secondRecipe instanceof BasicSoil bs) {
            return IngredientUtil.canConflict(firstRecipe.getIngredient(), bs.getIngredient());
        }
        return false;
    }
    
    @Override
    public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super BasicSoil> manager, BasicSoil recipe) {
        
        return Optional.of(IDecomposedRecipe.builder()
                .with(BuiltinRecipeComponents.Input.INGREDIENTS, IIngredient.fromIngredient(recipe.getIngredient()))
                .with(BPTweakerRecipeComponents.Metadata.DISPLAY_STATES, recipe.getDisplayState())
                .with(BPTweakerRecipeComponents.Metadata.GROWTH_MODIFIER, recipe.getGrowthModifier())
                .with(BPTweakerRecipeComponents.Metadata.SOIL_CATEGORY, new ArrayList<>(recipe.getCategories()))
                .with(BPTweakerRecipeComponents.Metadata.LIGHT_LEVEL, recipe.getLightLevel())
                .build()
        );
    }
    
    @Override
    public Optional<BasicSoil> recompose(IRecipeManager<? super BasicSoil> manager, ResourceLocation name, IDecomposedRecipe recipe) {
        
        Ingredient ingredient = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS)
                .asVanillaIngredient();
        DisplayState displayState = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Metadata.DISPLAY_STATES);
        float growthModifier = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Metadata.GROWTH_MODIFIER);
        Set<String> soilCategories = new HashSet<>(recipe.getOrThrow(BPTweakerRecipeComponents.Metadata.SOIL_CATEGORY));
        int lightLevel = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Metadata.LIGHT_LEVEL);
        return Optional.of(new BasicSoil(name, ingredient, displayState, growthModifier, soilCategories, lightLevel));
    }
    
}
