package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.Tags;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {
    @Inject(
            method = "getEnchantmentList",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private void getEnchantmentList(RegistryAccess access, ItemStack itemStack, int slot, int enchantmentCost, CallbackInfoReturnable<List<EnchantmentInstance>> cir, @Local(name = "$$5") List<EnchantmentInstance> list) {
        if (ConfigHelper.get(Config.enhanced_enchanting_exclusion)) {
            list.removeIf(enchantment -> enchantment.enchantment.is(Tags.EXCLUDED));
        }
    }
}
