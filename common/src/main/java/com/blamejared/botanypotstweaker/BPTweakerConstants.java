package com.blamejared.botanypotstweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Logger;

public class BPTweakerConstants {
    
    public static final String MOD_ID = "botanypotstweaker";
    public static final String MOD_NAME = "BotanyPotsTweaker";
    
    public static final Logger LOGGER = CraftTweakerAPI.getLogger(MOD_NAME);
    
    public static ResourceLocation rl(String path) {
        
        return new ResourceLocation(MOD_ID, path);
    }
    
}
