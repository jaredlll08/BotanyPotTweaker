package com.blamejared.botanypotstweaker.recipe.handler;

import com.blamejared.botanypotstweaker.recipe.replacement.BPTweakerRecipeComponents;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import net.darkhax.botanypots.data.recipes.fertilizer.BasicFertilizer;
import net.minecraft.Optionull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Optional;

@IRecipeHandler.For(BasicFertilizer.class)
public class BasicFertilizerHandler implements IRecipeHandler<BasicFertilizer> {
    
    @Override
    public String dumpToCommandString(IRecipeManager<? super BasicFertilizer> manager, BasicFertilizer recipe) {
        
        return "<recipetype:botanypots:fertilizer>.addFertilizer(%s, %s, %s, %s, %s, %s);".formatted(StringUtil.quoteAndEscape(recipe.getId()),
                IIngredient.fromIngredient(recipe.getIngredient()).getCommandString(),
                recipe.getMinTicks(),
                recipe.getMaxTicks(),
                Optionull.map(recipe.getCropIngredient(), ingredient -> IIngredient.fromIngredient(ingredient)
                        .getCommandString()),
                Optionull.map(recipe.getSoilIngredient(), ingredient -> IIngredient.fromIngredient(ingredient)
                        .getCommandString()));
    }
    
    @Override
    public <U extends Recipe<?>> boolean doesConflict(IRecipeManager<? super BasicFertilizer> manager, BasicFertilizer firstRecipe, U secondRecipe) {
        
        if(secondRecipe instanceof BasicFertilizer bf) {
            boolean ingredientMatches = IngredientUtil.canConflict(firstRecipe.getIngredient(), bf.getIngredient());
            boolean cropMatches = (firstRecipe.getCropIngredient() == null || bf.getCropIngredient() == null) ? firstRecipe.getCropIngredient() == bf.getCropIngredient() : IngredientUtil.canConflict(firstRecipe.getCropIngredient(), bf.getCropIngredient());
            boolean seedMatches = (firstRecipe.getSoilIngredient() == null || bf.getSoilIngredient() == null) ? firstRecipe.getSoilIngredient() == bf.getSoilIngredient() : IngredientUtil.canConflict(firstRecipe.getSoilIngredient(), bf.getSoilIngredient());
            return ingredientMatches && cropMatches && seedMatches;
        }
        return false;
    }
    
    @Override
    public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super BasicFertilizer> manager, BasicFertilizer recipe) {
        
        return Optional.of(IDecomposedRecipe.builder()
                .with(BuiltinRecipeComponents.Input.INGREDIENTS, IIngredient.fromIngredient(recipe.getIngredient()))
                .with(BPTweakerRecipeComponents.Input.CROP_INGREDIENT, Optionull.mapOrDefault(recipe.getCropIngredient(), IIngredient::fromIngredient, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Input.SOIL_INGREDIENT, Optionull.mapOrDefault(recipe.getSoilIngredient(), IIngredient::fromIngredient, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Output.MIN_TICKS, recipe.getMinTicks())
                .with(BPTweakerRecipeComponents.Output.MAX_TICKS, recipe.getMaxTicks())
                .build());
    }
    
    @Override
    public Optional<BasicFertilizer> recompose(IRecipeManager<? super BasicFertilizer> manager, ResourceLocation name, IDecomposedRecipe recipe) {
        //TODO Test ingredients since they can be null
        Ingredient ingredient = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS)
                .asVanillaIngredient();
        Ingredient cropIngredient = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Input.CROP_INGREDIENT)
                .asVanillaIngredient();
        Ingredient soilIngredient = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Input.SOIL_INGREDIENT)
                .asVanillaIngredient();
        int minTicks = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Output.MIN_TICKS);
        int maxTicks = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Output.MAX_TICKS);
        
        return Optional.of(new BasicFertilizer(name, ingredient, cropIngredient.isEmpty() ? null : cropIngredient, soilIngredient.isEmpty() ? null : soilIngredient, minTicks, maxTicks));
    }
    
}
