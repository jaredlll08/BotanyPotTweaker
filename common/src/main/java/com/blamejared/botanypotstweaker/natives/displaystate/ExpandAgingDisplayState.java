package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.AgingDisplayState;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/AgingDisplayState")
@NativeTypeRegistration(value = AgingDisplayState.class, zenCodeName = "mods.botanypotstweaker.displaystate.AgingDisplayState")
public class ExpandAgingDisplayState {
    
    /**
     * Creates a new AgingDisplayState from the given {@link BlockState}
     *
     * @param state The state to display
     *
     * @return a new display state from the given blockstate
     */
    @ZenCodeType.StaticExpansionMethod
    public static AgingDisplayState of(BlockState state) {
        
        return new AgingDisplayState(state);
    }
    
}
