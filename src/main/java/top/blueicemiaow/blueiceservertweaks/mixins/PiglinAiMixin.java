package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import org.spongepowered.asm.mixin.Mixin;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.Optional;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @WrapMethod(method = "isWearingGold")
    private static boolean isWearingGold(LivingEntity entity, Operation<Boolean> original) {
        if (ConfigHelper.get(Config.enhanced_is_wearing_gold)) {
            for (ItemStack itemStack : entity.getArmorSlots()) {
                if (itemStack.is(BlueIceServerTweaks.PIGLIN_SAFE_ARMOR)) {
                    return true;
                } else {
                    ArmorTrim trim = itemStack.get(DataComponents.TRIM);
                    if (trim != null) {
                        Optional<ResourceKey<TrimMaterial>> material = trim.material().unwrapKey();
                        if (material.isPresent()) {
                            ResourceKey<TrimMaterial> trimMaterial = material.get();
                            if (trimMaterial == TrimMaterials.GOLD || trimMaterial == TrimMaterials.NETHERITE) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return original.call(entity);
    }
}
