package com.blamejared.botanypotstweaker.recipe.handler;

import com.blamejared.botanypotstweaker.recipe.replacement.BPTweakerRecipeComponents;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.DecomposedRecipeBuilder;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.common.collect.Lists;
import net.darkhax.bookshelf.api.data.sound.Sound;
import net.darkhax.botanypots.data.recipes.potinteraction.BasicPotInteraction;
import net.minecraft.Optionull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@IRecipeHandler.For(BasicPotInteraction.class)
public class BasicPotInteractionHandler implements IRecipeHandler<BasicPotInteraction> {
    
    @Override
    public String dumpToCommandString(IRecipeManager<? super BasicPotInteraction> manager, BasicPotInteraction recipe) {
        
        return "<recipetype:botanypots:pot_interaction>.addInteraction(%s, %s, %s, %s, %s, %s, %s, %s, [%s]);".formatted(
                StringUtil.quoteAndEscape(recipe.getId()),
                IIngredient.fromIngredient(recipe.getHeldTest()).getCommandString(),
                recipe.isDamageHeld(),
                Optionull.map(recipe.getSoilTest(), ingredient -> IIngredient.fromIngredient(ingredient)
                        .getCommandString()),
                Optionull.map(recipe.getSeedTest(), ingredient -> IIngredient.fromIngredient(ingredient)
                        .getCommandString()),
                Optionull.map(recipe.getNewSoilStack(), stack -> IItemStack.of(stack).getCommandString()),
                Optionull.map(recipe.getNewSeedStack(), stack -> IItemStack.of(stack).getCommandString()),
                Optionull.map(recipe.getSound(), sound -> "Sound.of(%s)".formatted(Sound.SERIALIZER.toJSONString(sound))),
                recipe.getExtraDrops()
                        .stream()
                        .map(ItemStackUtil::getCommandString)
                        .collect(Collectors.joining(", "))
        );
    }
    
    @Override
    public <U extends Recipe<?>> boolean doesConflict(IRecipeManager<? super BasicPotInteraction> manager, BasicPotInteraction firstRecipe, U secondRecipe) {
        
        if(secondRecipe instanceof BasicPotInteraction bpi) {
            boolean ingredientMatches = IngredientUtil.canConflict(firstRecipe.getHeldTest(), bpi.getHeldTest());
            boolean soilMatches = (firstRecipe.getSoilTest() == null || bpi.getSoilTest() == null) ? firstRecipe.getSoilTest() == bpi.getSoilTest() : IngredientUtil.canConflict(firstRecipe.getSoilTest(), bpi.getSoilTest());
            boolean seedMatches = (firstRecipe.getSeedTest() == null || bpi.getSeedTest() == null) ? firstRecipe.getSeedTest() == bpi.getSeedTest() : IngredientUtil.canConflict(firstRecipe.getSeedTest(), bpi.getSeedTest());
            return ingredientMatches && soilMatches && seedMatches;
        }
        return false;
    }
    
    @Override
    public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super BasicPotInteraction> manager, BasicPotInteraction recipe) {
        
        DecomposedRecipeBuilder builder = IDecomposedRecipe.builder()
                .with(BuiltinRecipeComponents.Input.INGREDIENTS, IIngredient.fromIngredient(recipe.getHeldTest()))
                .with(BPTweakerRecipeComponents.Metadata.DAMAGE_HELD, recipe.isDamageHeld())
                .with(BPTweakerRecipeComponents.Input.SOIL_INGREDIENT, Optionull.mapOrDefault(recipe.getSoilTest(), IIngredient::fromIngredient, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Input.SEED_INGREDIENT, Optionull.mapOrDefault(recipe.getSeedTest(), IIngredient::fromIngredient, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Output.SOIL, Optionull.mapOrDefault(recipe.getNewSoilStack(), IItemStack::of, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Output.SEED, Optionull.mapOrDefault(recipe.getNewSeedStack(), IItemStack::of, IItemStack.empty()))
                .with(BPTweakerRecipeComponents.Output.EXTRA_DROPS, Lists.transform(recipe.getExtraDrops(), IItemStack::of));
        if(recipe.getSound() != null) {
            builder.with(BPTweakerRecipeComponents.Metadata.SOUND, recipe.getSound());
        }
        return Optional.of(builder.build());
    }
    
    @Override
    public Optional<BasicPotInteraction> recompose(IRecipeManager<? super BasicPotInteraction> manager, ResourceLocation name, IDecomposedRecipe recipe) {
        
        Ingredient heldTest = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS).asVanillaIngredient();
        boolean damageHeld = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Metadata.DAMAGE_HELD);
        Ingredient soilIngredient = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Input.SOIL_INGREDIENT)
                .asVanillaIngredient();
        Ingredient seedIngredient = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Input.SEED_INGREDIENT)
                .asVanillaIngredient();
        ItemStack soil = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Output.SOIL).getInternal();
        ItemStack seed = recipe.getOrThrowSingle(BPTweakerRecipeComponents.Output.SEED).getInternal();
        List<ItemStack> extraDrops = recipe.getOrThrow(BPTweakerRecipeComponents.Output.EXTRA_DROPS)
                .stream()
                .map(IItemStack::getInternal)
                .toList();
        Sound sound = Optionull.map(recipe.get(BPTweakerRecipeComponents.Metadata.SOUND), sounds -> sounds.get(0));
        return Optional.of(new BasicPotInteraction(name, heldTest, damageHeld, soilIngredient.isEmpty() ? null : soilIngredient, seedIngredient.isEmpty() ? null : seedIngredient, soil.isEmpty() ? null : soil, seed.isEmpty() ? null : seed, sound, extraDrops));
    }
    
}
