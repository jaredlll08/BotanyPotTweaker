package com.blamejared.botanypotstweaker.natives.fertilizer;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.recipes.fertilizer.BasicFertilizer;
import net.minecraft.Optionull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/fertilizer/BasicFertilizer")
@NativeTypeRegistration(value = BasicFertilizer.class, zenCodeName = "mods.botanypotstweaker.fertilizer.BasicFertilizer")
public class ExpandBasicFertilizer {
    
    @ZenCodeType.Getter("maxTicks")
    public static int getMaxTicks(BasicFertilizer internal) {
        
        return internal.getMaxTicks();
    }
    
    @ZenCodeType.Getter("minTicks")
    public static int getMinTicks(BasicFertilizer internal) {
        
        return internal.getMinTicks();
    }
    
    @ZenCodeType.Getter("soilIngredient")
    public static IIngredient getSoilIngredient(BasicFertilizer internal) {
        
        return Optionull.map(internal.getSoilIngredient(), IIngredient::fromIngredient);
    }
    
    @ZenCodeType.Getter("cropIngredient")
    public static IIngredient getCropIngredient(BasicFertilizer internal) {
        
        return Optionull.map(internal.getCropIngredient(), IIngredient::fromIngredient);
    }
    
    @ZenCodeType.Getter("ingredient")
    public static IIngredient getIngredient(BasicFertilizer internal) {
        
        return IIngredient.fromIngredient(internal.getIngredient());
    }
    
}
