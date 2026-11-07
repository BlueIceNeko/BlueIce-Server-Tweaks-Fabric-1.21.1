package top.blueicemiaow.blueiceservertweaks.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;

public class Tags {
    public static final TagKey<Block>       COLLECTABLE = TagKey.create(Registries.BLOCK,       getIdentifier("collectable"));

    public static final TagKey<Item>        CAN_DISABLE_SHIELD = TagKey.create(Registries.ITEM, getIdentifier("can_disable_shield"));
    public static final TagKey<Item>        PIGLIN_SAFE_ARMOR = TagKey.create(Registries.ITEM,  getIdentifier("piglin_safe_armor"));

    public static final TagKey<Enchantment> EXCLUDED = TagKey.create(Registries.ENCHANTMENT,    getIdentifier("excluded"));

    public static ResourceLocation getIdentifier(String key) {
        return ResourceLocation.fromNamespaceAndPath(BlueIceServerTweaks.MODID, key);
    }
}
