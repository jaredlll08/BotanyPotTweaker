package com.blamejared.botanypotstweaker.recipe.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.google.common.collect.Lists;
import net.darkhax.bookshelf.api.data.sound.Sound;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.data.recipes.potinteraction.BasicPotInteraction;
import net.darkhax.botanypots.data.recipes.potinteraction.PotInteraction;
import net.minecraft.Optionull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

/**
 * @docParam this <recipetype:botanypots:pot_interaction>
 */
@ZenRegister
@ZenCodeType.Name("mods.botanypots.PotInteractionManager")
@Document("mods/BotanyPotsTweaker/PotInteractionManager")
public class PotInteractionManager implements IRecipeManager<PotInteraction> {
    
    /**
     * Adds a new interaction to the pot.
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
     * @docParam id "interaction_test"
     * @docParam heldTest <item:minecraft:diamond>
     * @docParam damageHeld false
     * @docParam soilTest null
     * @docParam seedTest null
     * @docParam newSoilStack null
     * @docParam newSeedStack <item:minecraft:iron_ingot>
     * @docParam sound Sound.of(<soundevent:minecraft:ambient.basalt_deltas.additions>, <constant:minecraft:sound/source:neutral>, 1,1)
     * @docParam extraDrops [<item:minecraft:stick>]
     */
    @ZenCodeType.Method
    public void addInteraction(String id, IIngredient heldTest, boolean damageHeld, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient soilTest, @ZenCodeType.Optional @ZenCodeType.Nullable IIngredient seedTest, @ZenCodeType.Optional @ZenCodeType.Nullable IItemStack newSoilStack, @ZenCodeType.Optional @ZenCodeType.Nullable IItemStack newSeedStack, @ZenCodeType.Optional @ZenCodeType.Nullable Sound sound, @ZenCodeType.Optional("[] as crafttweaker.api.item.IItemStack[]") IItemStack[] extraDrops) {
        
        ResourceLocation rl = CraftTweakerConstants.rl(fixRecipeName(id));
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new BasicPotInteraction(rl,
                heldTest.asVanillaIngredient(),
                damageHeld,
                Optionull.map(soilTest, IIngredient::asVanillaIngredient),
                Optionull.map(seedTest, IIngredient::asVanillaIngredient),
                Optionull.map(newSoilStack, IItemStack::getInternal),
                Optionull.map(newSeedStack, IItemStack::getInternal),
                sound,
                Lists.transform(Arrays.asList(extraDrops), IItemStack::getInternal))));
    }
    
    @Override
    public void remove(IIngredient output) {
        
        throw new UnsupportedOperationException("Unable to remove a PotInteraction by its output!");
    }
    
    @Override
    public void removeByInput(IItemStack input) {
        
        throw new UnsupportedOperationException("Unable to remove a PotInteraction by its input!");
    }
    
    @Override
    public RecipeType<PotInteraction> getRecipeType() {
        
        return BotanyPotHelper.POT_INTERACTION_TYPE.get();
    }
    
}
