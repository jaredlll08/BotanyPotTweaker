package com.blamejared.botanypotstweaker.natives.potinteraction;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.visitor.DataToJsonStringVisitor;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.gson.JsonObject;
import net.darkhax.bookshelf.api.data.sound.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/potinteraction/Sound")
@NativeTypeRegistration(value = Sound.class, zenCodeName = "mods.botanypotstweaker.potinteraction.Sound")
public class ExpandSound {
    
    /**
     * Convert a MapData object to a Sound object.
     *
     * @param data The MapData object to convert.
     *
     * @return The converted Sound object.
     */
    @ZenCodeType.StaticExpansionMethod
    public static Sound of(MapData data) {
        
        return Sound.SERIALIZER.fromJSON(IRecipeManager.JSON_RECIPE_GSON.fromJson(data.accept(DataToJsonStringVisitor.INSTANCE), JsonObject.class));
    }
    
    /**
     * Create a new Sound object with the given parameters.
     *
     * @param sound    The SoundEvent to use for the sound.
     * @param category The SoundSource category of the sound.
     * @param pitch    The pitch of the sound.
     * @param volume   The volume of the sound.
     *
     * @return The created Sound object.
     */
    @ZenCodeType.StaticExpansionMethod
    public static Sound of(SoundEvent sound, SoundSource category, float pitch, float volume) {
        
        return new Sound(sound, category, pitch, volume);
    }
    
}
