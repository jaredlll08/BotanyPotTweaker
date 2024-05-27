package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.SimpleDisplayState;
import net.darkhax.botanypots.data.displaystate.math.AxisAlignedRotation;
import net.minecraft.Optionull;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Optional;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/SimpleDisplayState")
@NativeTypeRegistration(value = SimpleDisplayState.class, zenCodeName = "mods.botanypotstweaker.displaystate.SimpleDisplayState")
public class ExpandSimpleDisplayState {
    
    @ZenCodeType.StaticExpansionMethod
    public static SimpleDisplayState of(BlockState state,
                                        @ZenCodeType.Optional @ZenCodeType.Nullable float[] scale,
                                        @ZenCodeType.Optional @ZenCodeType.Nullable float[] offset,
                                        @ZenCodeType.Optional @ZenCodeType.Nullable List<AxisAlignedRotation> rotations,
                                        @ZenCodeType.OptionalBoolean boolean renderFluid) {
        
        return new SimpleDisplayState(state,
                Optional.ofNullable(Optionull.map(scale, Vector3f::new)),
                Optional.ofNullable(Optionull.map(offset, Vector3f::new)),
                rotations == null ? List.of() : rotations,
                renderFluid);
    }
    
}
