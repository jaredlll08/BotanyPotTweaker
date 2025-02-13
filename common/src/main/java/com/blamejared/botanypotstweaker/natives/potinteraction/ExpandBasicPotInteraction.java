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
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@Document("mods/BotanyPotsTweaker/potinteraction/BasicPotInteraction")
@NativeTypeRegistration(value = BasicPotInteraction.class, zenCodeName = "mods.botanypotstweaker.potinteraction.BasicPotInteraction")
public class ExpandBasicPotInteraction {
    
    
    /**
     * Creates a new PotInteraction
     *
     * @param id           The ID of the interaction.
     * @param heldTest     The ingredient that should be held
     * @param damageHeld   Whether the held item should be damaged during the interaction.
     * @param soilTest     What soil does this interaction happen on.
     * @param seedTest     What seed is this interaction for
     * @param newSoilStack The new soil stack to replace the current soil.
     * @param newSeedStack The new seed stack to replace the current seed.
     * @param sound        The sound to play during the interaction.
     * @param extraDrops   Additional items to drop during the interaction.
     *
     * @docParam id <resource:crafttweaker:interaction_test>
     * @docParam heldTest <item:minecraft:diamond>
     * @docParam damageHeld false
     * @docParam soilTest null
     * @docParam seedTest null
     * @docParam newSoilStack null
     * @docParam newSeedStack <item:minecraft:iron_ingot>
     * @docParam sound Sound.of(<soundevent:minecraft:ambient.basalt_deltas.additions>, <constant:minecraft:sound/source:neutral>, 1,1)
     * @docParam extraDrops [<item:minecraft:stick>]
     */
    @ZenCodeType.StaticExpansionMethod
    public static BasicPotInteraction of(ResourceLocation id, IIngredient heldTest, boolean damageHeld, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient soilTest, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient seedTest, @ZenCodeType.Optional @ZenCodeType.Nullable IItemStack newSoilStack, @ZenCodeType.Optional @ZenCodeType.Nullable IItemStack newSeedStack, @ZenCodeType.Optional @ZenCodeType.Nullable Sound sound, @ZenCodeType.Optional("[] as crafttweaker.api.item.IItemStack[]") IItemStack[] extraDrops) {
        
        return new BasicPotInteraction(id,
                heldTest.asVanillaIngredient(),
                damageHeld,
                Optionull.map(soilTest, IIngredient::asVanillaIngredient),
                Optionull.map(seedTest, IIngredient::asVanillaIngredient),
                Optionull.map(newSoilStack, IItemStack::getInternal),
                Optionull.map(newSeedStack, IItemStack::getInternal),
                sound,
                Lists.transform(Arrays.asList(extraDrops), IItemStack::getInternal));
    }
    
    /**
     * Gets a list of extra items that will drop when this interaction happens.
     *
     * @return A list of extra items that will drop when this interaction happens.
     */
    @ZenCodeType.Getter("extraDrops")
    public static IItemStack[] getExtraDrops(BasicPotInteraction internal) {
        
        return Lists.transform(internal.getExtraDrops(), IItemStack::of).toArray(IItemStack[]::new);
    }
    
    /**
     * Gets the sound that will play when this interaction happens.
     *
     * @return The sound that will play when this interaction happens.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("sound")
    public static Sound getSound(BasicPotInteraction internal) {
        
        return internal.getSound();
    }
    
    /**
     * Gets the seed stack after the interaction changes it, if the interaction doesn't change it, this will return null
     *
     * @return the seed stack after the interaction changes it
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("newSeedStack")
    public static IItemStack getNewSeedStack(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getNewSeedStack(), IItemStack::of);
    }
    
    /**
     * Gets the soil stack after the interaction changes it, if the interaction doesn't change it, this will return null
     *
     * @return the soil stack after the interaction changes it
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("newSoilStack")
    public static IItemStack getNewSoilStack(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getNewSoilStack(), IItemStack::of);
    }
    
    /**
     * Gets an ingredient that defines which seeds this interaction can work with.
     *
     * @return an ingredient that defines which seeds this interaction can work with.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("seedTest")
    public static IIngredient getSeedTest(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getSeedTest(), IIngredient::fromIngredient);
    }
    
    /**
     * Gets an ingredient that defines which soils this interaction can work with.
     *
     * @return an ingredient that defines which soils this interaction can work with.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("soilTest")
    public static IIngredient getSoilTest(BasicPotInteraction internal) {
        
        return Optionull.map(internal.getSoilTest(), IIngredient::fromIngredient);
    }
    
    /**
     * Checks if this interaction damages the held item
     *
     * @return true if this interaction damages the held item, false otherwise.
     */
    @ZenCodeType.Getter("damageHeld")
    public static boolean isDamageHeld(BasicPotInteraction internal) {
        
        return internal.isDamageHeld();
    }
    
    /**
     * Gets an ingredient that defines what causes this interaction to happen.
     *
     * @return an ingredient that defines what causes this interaction to happen.
     */
    @ZenCodeType.Getter("heldTest")
    public static IIngredient getHeldTest(BasicPotInteraction internal) {
        
        return IIngredient.fromIngredient(internal.getHeldTest());
    }
    
}
