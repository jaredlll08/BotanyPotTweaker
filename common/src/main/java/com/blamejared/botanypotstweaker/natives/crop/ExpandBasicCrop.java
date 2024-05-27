package com.blamejared.botanypotstweaker.natives.crop;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.crop.BasicCrop;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Set;

@ZenRegister
@Document("mods/BotanyPotsTweaker/crop/BasicCrop")
@NativeTypeRegistration(value = BasicCrop.class, zenCodeName = "mods.botanypotstweaker.crop.BasicCrop")
public class ExpandBasicCrop {
    
    @ZenCodeType.Getter("lightLevel")
    public static int getLightLevel(BasicCrop internal) {
        
        return internal.getLightLevel();
    }
    
    @ZenCodeType.Getter("displayStates")
    public static List<DisplayState> getDisplayStates(BasicCrop internal) {
        
        return internal.getDisplayStates();
    }
    
    @ZenCodeType.Getter("results")
    public static List<HarvestEntry> getResults(BasicCrop internal) {
        
        return internal.getResults();
    }
    
    @ZenCodeType.Getter("growthTicks")
    public static int getGrowthTicks(BasicCrop internal) {
        
        return internal.getGrowthTicks();
    }
    
    @ZenCodeType.Getter("soilCategories")
    public static Set<String> getSoilCategories(BasicCrop internal) {
        
        return internal.getSoilCategories();
    }
    
    @ZenCodeType.Getter("seed")
    public static IIngredient getSeed(BasicCrop internal) {
        
        return IIngredient.fromIngredient(internal.getSeed());
    }
    
}
