package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.visitor.DataToJsonStringVisitor;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.gson.JsonObject;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/DisplayState")
@NativeTypeRegistration(value = DisplayState.class, zenCodeName = "mods.botanypotstweaker.displaystate.DisplayState")
public class ExpandDisplayState {
    
    @ZenCodeType.StaticExpansionMethod
    public static DisplayState of(MapData data) {
        
        return DisplayState.SERIALIZER.fromJSON(IRecipeManager.JSON_RECIPE_GSON.fromJson(data.accept(DataToJsonStringVisitor.INSTANCE), JsonObject.class));
    }
    
}
