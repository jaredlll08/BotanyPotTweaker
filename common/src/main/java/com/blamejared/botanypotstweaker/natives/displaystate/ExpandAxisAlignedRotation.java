package com.blamejared.botanypotstweaker.natives.displaystate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.darkhax.botanypots.data.displaystate.math.AxisAlignedRotation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/BotanyPotsTweaker/displaystate/AxisAlignedRotation")
@NativeTypeRegistration(value = AxisAlignedRotation.class, zenCodeName = "mods.botanypotstweaker.displaystate.AxisAlignedRotation")
@BracketEnum("botanypots:axis_aligned_rotation")
public class ExpandAxisAlignedRotation {
    
    /**
     * A Quaternion that contains the rotational information. In this case it represents a 0, 90, 180, or 270-degree
     * rotation along the X, Y, or Z axis.
     */
    @ZenCodeType.Getter("rotation")
    public static Quaternionf rotation(AxisAlignedRotation internal) {
        
        return internal.rotation;
    }
    
    /**
     * A predetermined offset that will realign the render when translated.
     */
    @ZenCodeType.Getter("offset")
    public static Vector3f offset(AxisAlignedRotation internal) {
        
        return internal.offset;
    }
    
}
