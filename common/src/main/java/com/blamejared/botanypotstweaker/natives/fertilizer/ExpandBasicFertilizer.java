package com.blamejared.botanypotstweaker.natives.fertilizer;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.recipes.fertilizer.BasicFertilizer;
import net.minecraft.Optionull;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/fertilizer/BasicFertilizer")
@NativeTypeRegistration(value = BasicFertilizer.class, zenCodeName = "mods.botanypotstweaker.fertilizer.BasicFertilizer")
public class ExpandBasicFertilizer {
    
    /**
     * Creates a new BasicFertilizer
     *
     * @param id             The ID of the fertilizer.
     * @param ingredient     The item that triggers the fertilization.
     * @param cropIngredient The ingredient for the crop that the fertilizer affects.
     * @param soilIngredient The ingredient for the soil that the fertilizer affects.
     * @param minTicks       The minimum amount of ticks that the fertilizer applies.
     * @param maxTicks       The maximum amount of ticks that the fertilizer applies.
     *
     * @docParam id <resource:crafttweaker:fertilizer_test>
     * @docParam ingredient <item:minecraft:diamond>
     * @docParam minTicks 20
     * @docParam maxTicks 40
     * @docParam cropIngredient null
     * @docParam soilIngredient <item:minecraft:dirt>
     */
    @ZenCodeType.StaticExpansionMethod
    public static BasicFertilizer of(ResourceLocation id, IIngredient ingredient, int minTicks, int maxTicks, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient cropIngredient, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient soilIngredient) {
        
        return new BasicFertilizer(id, ingredient.asVanillaIngredient(), Optionull.map(cropIngredient, IIngredient::asVanillaIngredient), Optionull.map(soilIngredient, IIngredient::asVanillaIngredient), minTicks, maxTicks);
    }
    
    /**
     * Gets the maximum amount of ticks that will be added to the growth
     *
     * @return the maximum ticks to add to the growth
     */
    @ZenCodeType.Getter("maxTicks")
    public static int getMaxTicks(BasicFertilizer internal) {
        
        return internal.getMaxTicks();
    }
    
    /**
     * Gets the minimum amount of ticks that will be added to the growth
     *
     * @return the minimum amount of ticks that will be added to the growth
     */
    @ZenCodeType.Getter("minTicks")
    public static int getMinTicks(BasicFertilizer internal) {
        
        return internal.getMinTicks();
    }
    
    /**
     * Gets the soil that this fertilizer works with.
     *
     * @return the soil that this fertilizer works with.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("soilIngredient")
    public static IIngredient getSoilIngredient(BasicFertilizer internal) {
        
        return Optionull.map(internal.getSoilIngredient(), IIngredient::fromIngredient);
    }
    
    /**
     * Gets the crop that this fertilizer works with.
     *
     * @return the crop that this fertilizer works with.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("cropIngredient")
    public static IIngredient getCropIngredient(BasicFertilizer internal) {
        
        return Optionull.map(internal.getCropIngredient(), IIngredient::fromIngredient);
    }
    
    /**
     * Gets the actual fertilizer ingredient, held by the hand.
     *
     * @return the actual fertilizer ingredient
     */
    @ZenCodeType.Getter("ingredient")
    public static IIngredient getIngredient(BasicFertilizer internal) {
        
        return IIngredient.fromIngredient(internal.getIngredient());
    }
    
}
