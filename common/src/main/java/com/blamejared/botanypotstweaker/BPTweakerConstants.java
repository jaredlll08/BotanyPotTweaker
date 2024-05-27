package com.blamejared.botanypotstweaker;

import net.minecraft.resources.ResourceLocation;

public class BPTweakerConstants {
    
    public static final String MOD_ID = "botanypotstweaker";
    public static final String MOD_NAME = "BotanyPotsTweaker";
    
    public static ResourceLocation rl(String path) {
        
        return new ResourceLocation(MOD_ID, path);
    }
    
}
