package com.blamejared.botanypotstweaker.natives.potinteraction;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Lists;
import net.darkhax.bookshelf.api.data.sound.Sound;
import net.darkhax.botanypots.data.recipes.potinteraction.BasicPotInteraction;
import net.minecraft.Optionull;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("mods/BotanyPotsTweaker/potinteraction/BasicPotInteraction")
@NativeTypeRegistration(value = BasicPotInteraction.class, zenCodeName = "mods.botanypotstweaker.potinteraction.BasicPotInteraction")
public class ExpandBasicPotInteraction {
    
    @ZenCodeType.Getter("extraDrops")
    public static List<IItemStack> getExtraDrops(BasicPotInteraction internal) {
        
        return Lists.transform(internal.getExtraDrops(), IItemStack::of);
    }
    
    @ZenCodeType.Getter("sound")
    @ZenCodeType.Nullable
    public static Sound getSound(BasicPotInteraction internal) {
        
        return internal.getSound();
    }
    
    @ZenCodeType.Getter("newSeedStack")
    public static IItemStack getNewSeedStack(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getNewSeedStack(), IItemStack::of);
    }
    
    @ZenCodeType.Getter("newSoilStack")
    public static IItemStack getNewSoilStack(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getNewSoilStack(), IItemStack::of);
    }
    
    @ZenCodeType.Getter("seedTest")
    public static IIngredient getSeedTest(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getSeedTest(), IIngredient::fromIngredient);
    }
    
    @ZenCodeType.Getter("soilTest")
    public static IIngredient getSoilTest(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getSoilTest(), IIngredient::fromIngredient);
    }
    
    @ZenCodeType.Getter("damageHeld")
    public static boolean isDamageHeld(BasicPotInteraction internal) {
        
        return internal.isDamageHeld();
    }
    
    @ZenCodeType.Getter("heldTest")
    public static IIngredient getHeldTest(BasicPotInteraction internal) {
        
        return IIngredient.fromIngredient(internal.getHeldTest());
    }
    
}
