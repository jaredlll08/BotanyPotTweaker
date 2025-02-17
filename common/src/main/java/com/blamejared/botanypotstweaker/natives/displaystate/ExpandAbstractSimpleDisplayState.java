package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.AbstractSimpleDisplayState;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/AbstractSimpleDisplayState")
@NativeTypeRegistration(value = AbstractSimpleDisplayState.class, zenCodeName = "mods.botanypotstweaker.displaystate.AbstractSimpleDisplayState")
public class ExpandAbstractSimpleDisplayState {
    
    /**
     * Gets the {@link BlockState} that will render for the given progress.
     *
     * @param progress the progress to render at
     *
     * @return the BlockState that will render for the given progress
     */
    @ZenCodeType.Method
    public static BlockState getRenderState(AbstractSimpleDisplayState internal, float progress) {
        
        return internal.getRenderState(progress);
    }
    
}
