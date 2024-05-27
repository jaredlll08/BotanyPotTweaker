package com.blamejared.botanypotstweaker.expand;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.darkhax.botanypots.data.displaystate.AgingDisplayState;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.block.BlockState")
@Document("mods/BotanyPotsTweaker/expands/BlockStateExpansions")
public class BPTExpandBlockState {
    
    /**
     * Converts a block state into a display state.
     *
     * @param blockState the block state to convert
     * @return the converted display state
     */
    @ZenCodeType.Caster(implicit = true)
    public static DisplayState asDisplayState(BlockState blockState) {
        
        return new AgingDisplayState(blockState);
    }
    
}
