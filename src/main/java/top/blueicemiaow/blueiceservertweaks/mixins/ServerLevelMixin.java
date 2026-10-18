package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @Inject(method = "addEntity", at = @At(value = "HEAD"), cancellable = true)
    private static void addEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (ConfigHelper.get(Config.prevent_rotten_flesh_drop) && entity instanceof ItemEntity item && item.getItem().is(Items.ROTTEN_FLESH)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
