package com.blamejared.botanypotstweaker.natives.soil;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.soil.BasicSoil;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Set;

@ZenRegister
@Document("mods/BotanyPotsTweaker/soil/BasicSoil")
@NativeTypeRegistration(value = BasicSoil.class, zenCodeName = "mods.botanypotstweaker.soil.BasicSoil")
public class ExpandBasicSoil {
    
    @ZenCodeType.Getter("lightLevel")
    public static int getLightLevel(BasicSoil internal) {
        
        return internal.getLightLevel();
    }
    
    @ZenCodeType.Getter("categories")
    public static Set<String> getCategories(BasicSoil internal) {
        
        return internal.getCategories();
    }
    
    @ZenCodeType.Getter("growthModifier")
    public static float getGrowthModifier(BasicSoil internal) {
        
        return internal.getGrowthModifier();
    }
    
    @ZenCodeType.Getter("displayState")
    public static DisplayState getDisplayState(BasicSoil internal) {
        
        return internal.getDisplayState();
    }
    
    @ZenCodeType.Getter("ingredient")
    public static IIngredient getIngredient(BasicSoil internal) {
        
        return IIngredient.fromIngredient(internal.getIngredient());
    }
    
}
