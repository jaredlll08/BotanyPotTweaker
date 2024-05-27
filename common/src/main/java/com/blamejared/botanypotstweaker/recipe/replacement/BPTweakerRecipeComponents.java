package com.blamejared.botanypotstweaker.recipe.replacement;

import com.blamejared.botanypotstweaker.BPTweakerConstants;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.type.IIngredientEmpty;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import com.google.gson.reflect.TypeToken;
import net.darkhax.bookshelf.api.data.sound.Sound;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;

import java.util.Arrays;
import java.util.Objects;

public final class BPTweakerRecipeComponents {
    
    public static final class Metadata {
        
        public static final IRecipeComponent<String> SOIL_CATEGORY = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/soil_category"),
                new TypeToken<>() {},
                Object::equals
        );
        
        public static final IRecipeComponent<DisplayState> DISPLAY_STATES = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/display_state"),
                new TypeToken<>() {},
                BPTweakerRecipeComponentEqualityCheckers::areDisplayStatesEqual
        );
        
        public static final IRecipeComponent<Float> GROWTH_MODIFIER = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/growth_modifier"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areNumbersEqual
        );
        
        public static final IRecipeComponent<Integer> LIGHT_LEVEL = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/light_level"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areNumbersEqual
        );
        
        public static final IRecipeComponent<Boolean> DAMAGE_HELD = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/damage_held"),
                new TypeToken<>() {},
                Boolean::equals
        );
        
        public static final IRecipeComponent<Sound> SOUND = IRecipeComponent.simple(
                BPTweakerConstants.rl("metadata/sound"),
                new TypeToken<>() {},
                Objects::equals
        );
        
    }
    
    public static final class Input {
        
        public static final IRecipeComponent<IIngredient> CROP_INGREDIENT = IRecipeComponent.composite(
                BPTweakerConstants.rl("input/crop_ingredient"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areIngredientsEqual,
                ingredient -> Arrays.asList(ingredient.getItems()),
                items -> items.isEmpty() ? IIngredientEmpty.getInstance() : items.stream()
                        .reduce(IIngredient::or)
                        .orElseThrow()
        );
        
        public static final IRecipeComponent<IIngredient> SOIL_INGREDIENT = IRecipeComponent.composite(
                BPTweakerConstants.rl("input/soil_ingredient"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areIngredientsEqual,
                ingredient -> Arrays.asList(ingredient.getItems()),
                items -> items.isEmpty() ? IIngredientEmpty.getInstance() : items.stream()
                        .reduce(IIngredient::or)
                        .orElseThrow()
        );
        public static final IRecipeComponent<IIngredient> SEED_INGREDIENT = IRecipeComponent.composite(
                BPTweakerConstants.rl("input/seed_ingredient"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areIngredientsEqual,
                ingredient -> Arrays.asList(ingredient.getItems()),
                items -> items.isEmpty() ? IIngredientEmpty.getInstance() : items.stream()
                        .reduce(IIngredient::or)
                        .orElseThrow()
        );
        
        private Input() {
        
        }
        
    }
    
    public static final class Output {
        
        public static final IRecipeComponent<HarvestEntry> HARVEST_ENTRIES = IRecipeComponent.simple(BPTweakerConstants.rl("output/harvest_entry"),
                new TypeToken<>() {},
                BPTweakerRecipeComponentEqualityCheckers::areHarvestEntriesEqual);
        
        public static final IRecipeComponent<Integer> MIN_TICKS = IRecipeComponent.simple(
                BPTweakerConstants.rl("output/min_ticks"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areNumbersEqual
        );
        
        public static final IRecipeComponent<Integer> MAX_TICKS = IRecipeComponent.simple(
                BPTweakerConstants.rl("output/max_ticks"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areNumbersEqual
        );
        
        public static final IRecipeComponent<IItemStack> SEED = IRecipeComponent.simple(
                CraftTweakerConstants.rl("output/seed"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areStacksEqual
        );
        public static final IRecipeComponent<IItemStack> SOIL = IRecipeComponent.simple(
                CraftTweakerConstants.rl("output/soil"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areStacksEqual
        );
        
        public static final IRecipeComponent<IItemStack> EXTRA_DROPS = IRecipeComponent.simple(
                CraftTweakerConstants.rl("output/extra_drops"),
                new TypeToken<>() {},
                RecipeComponentEqualityCheckers::areStacksEqual
        );
        
        private Output() {
        
        }
        
    }
    
}
