package com.blamejared.botanypotstweaker.natives.soil;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.recipes.soil.BasicSoil;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ZenRegister
@Document("mods/BotanyPotsTweaker/soil/BasicSoil")
@NativeTypeRegistration(value = BasicSoil.class, zenCodeName = "mods.botanypotstweaker.soil.BasicSoil")
public class ExpandBasicSoil {
    
    
    /**
     * Creates a new BasicSoil
     *
     * @param id             The ID of the soil.
     * @param ingredient     The ingredient that represents the soil.
     * @param displayState    The display state of the soil.
     * @param categories     The categories that the soil belongs to.
     * @param growthModifier The optional growth modifier of the soil. Defaults to 1.
     * @param lightLevel     The optional light level that the soil gives. Defaults to 0.
     *
     * @docParam id <resource:crafttweaker:soil_test>
     * @docParam ingredient <item:minecraft:dirt>
     * @docParam displayState SimpleDisplayState.of(<blockstate:minecraft:diamond_block>)
     * @docParam categories ["category1", "category2"]
     * @docParam growthModifier 1.5
     * @docParam lightLevel 7
     */
    @ZenCodeType.StaticExpansionMethod
    public static BasicSoil of(ResourceLocation id, IIngredient ingredient, DisplayState displayState, List<String> categories, @ZenCodeType.OptionalFloat(1) float growthModifier, @ZenCodeType.OptionalInt int lightLevel) {
        
        return new BasicSoil(id, ingredient.asVanillaIngredient(), displayState, growthModifier, new HashSet<>(categories), lightLevel);
    }
    
    /**
     * Gets the light level that this soil gives off.
     *
     * @return the light level that this soil gives off.
     */
    @ZenCodeType.Getter("lightLevel")
    public static int getLightLevel(BasicSoil internal) {
        
        return internal.getLightLevel();
    }
    
    /**
     * Gets a list of categories that this soil is part of.
     *
     * @return a list of categories that this soil is part of.
     */
    @ZenCodeType.Getter("categories")
    public static List<String> getCategories(BasicSoil internal) {
        
        return new ArrayList<>(internal.getCategories());
    }
    
    /**
     * Gets the growth modifier that this soil applies
     *
     * @return the growth modifier that this soil applies
     */
    @ZenCodeType.Getter("growthModifier")
    public static float getGrowthModifier(BasicSoil internal) {
        
        return internal.getGrowthModifier();
    }
    
    /**
     * Gets the {@link DisplayState} of this soil
     *
     * @return the {@link DisplayState} of this soil
     */
    @ZenCodeType.Getter("displayState")
    public static DisplayState getDisplayState(BasicSoil internal) {
        
        return internal.getDisplayState();
    }
    
    /**
     * Gets the ingredient of this soil.
     *
     * @return the ingredient of this soil.
     */
    @ZenCodeType.Getter("ingredient")
    public static IIngredient getIngredient(BasicSoil internal) {
        
        return IIngredient.fromIngredient(internal.getIngredient());
    }
    
}
