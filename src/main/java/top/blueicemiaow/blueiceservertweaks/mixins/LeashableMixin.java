package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(Leashable.class)
public interface LeashableMixin {
    @ModifyConstant(method = "tickLeash", constant = @Constant(doubleValue = 10))
    private static double tickLeash(double constant) {
        return ConfigHelper.get(Config.remove_lead_length_limit) ? Double.MAX_VALUE : constant;
    }
}
