package com.blamejared.botanypotstweaker.recipe.replacement;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;

import java.util.Objects;

public class BPTweakerRecipeComponentEqualityCheckers {
    
    public static boolean areDisplayStatesEqual(final DisplayState a, final DisplayState b) {
        
        if(Objects.equals(a, b)) {
            return true;
        }
        return DisplayState.SERIALIZER.toJSON(a).equals(DisplayState.SERIALIZER.toJSON(b));
    }
    
    public static boolean areHarvestEntriesEqual(final HarvestEntry a, final HarvestEntry b) {
        
        if(Objects.equals(a, b)) {
            return true;
        }
        return a.getChance() == b.getChance() && a.getMinRolls() == b.getMinRolls() && a.getMaxRolls() == b.getMaxRolls() && RecipeComponentEqualityCheckers.areStacksEqual(IItemStack.of(a.getItem()), IItemStack.of(b.getItem()));
    }
    
}
