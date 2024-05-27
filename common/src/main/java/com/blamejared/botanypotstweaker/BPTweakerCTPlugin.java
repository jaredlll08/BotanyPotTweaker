package com.blamejared.botanypotstweaker;

import com.blamejared.botanypotstweaker.recipe.replacement.BPTweakerRecipeComponents;
import com.blamejared.crafttweaker.api.plugin.CraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ICraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.IRecipeComponentRegistrationHandler;

@CraftTweakerPlugin("botanypotstweaker:main")
public class BPTweakerCTPlugin implements ICraftTweakerPlugin {
    
    @Override
    public void registerRecipeComponents(IRecipeComponentRegistrationHandler handler) {
        
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Input.CROP_INGREDIENT);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Input.SEED_INGREDIENT);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Input.SOIL_INGREDIENT);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.DAMAGE_HELD);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.DISPLAY_STATES);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.GROWTH_MODIFIER);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.LIGHT_LEVEL);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.SOIL_CATEGORY);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Metadata.SOUND);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.EXTRA_DROPS);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.HARVEST_ENTRIES);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.MAX_TICKS);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.MIN_TICKS);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.SEED);
        handler.registerRecipeComponent(BPTweakerRecipeComponents.Output.SOIL);
    }
    
}
