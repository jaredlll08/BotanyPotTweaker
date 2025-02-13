package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.displaystate.TransitionalDisplayState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/TransitionalDisplayState")
@NativeTypeRegistration(value = TransitionalDisplayState.class, zenCodeName = "mods.botanypotstweaker.displaystate.TransitionalDisplayState")
public class ExpandTransitionalDisplayState {
    
    /**
     * Creates a TransitionalDisplayState object from a given DisplayState.
     *
     * @param state The DisplayState object to be included in the TransitionalDisplayState.
     * @return A TransitionalDisplayState object created from the given DisplayState.
     */
    @ZenCodeType.StaticExpansionMethod
    public static TransitionalDisplayState of(DisplayState state) {
        
        return of(List.of(state));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static TransitionalDisplayState of(List<DisplayState> states) {
        
        return new TransitionalDisplayState(states);
    }
    
    
}
