package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @ModifyConstant(method = "getDestroySpeed", constant = @Constant(floatValue = 0.0027f))
    public float getDestroySpeed2(float original) {
        return ConfigHelper.get(Config.mining_fatigue_fix) ? 0.027f : original;
    }

    @ModifyConstant(method = "getDestroySpeed", constant = @Constant(floatValue = 8.1E-4f))
    public float getDestroySpeed_(float original) {
        return ConfigHelper.get(Config.mining_fatigue_fix) ? 8.1E-3f : original;
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Mth;clamp(DDD)D"
            )
    )
    private double tick(double value, double min, double max, Operation<Double> original) {
        return ConfigHelper.get(Config.remove_player_movement_bound) ? value : original.call(value, min, max);
    }
}
