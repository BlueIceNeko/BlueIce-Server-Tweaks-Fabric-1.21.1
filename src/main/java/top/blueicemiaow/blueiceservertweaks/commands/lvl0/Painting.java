package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class Painting {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("painting").then(Commands.argument("id", StringArgumentType.greedyString()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "painting")) {
                String id = StringArgumentType.getString(arguments, "id");
                String snbt = "{id:\"minecraft:painting\",variant:\"" + id + "\"}";
                CompoundTag nbt;
                try {
                    nbt = TagParser.parseTag(snbt);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return 0;
                }
                ItemStack painting = new ItemStack(Items.PAINTING);
                painting.set(DataComponents.ENTITY_DATA, CustomData.of(nbt));
                painting.setCount(1);
                player.getInventory().add(painting);
                BlueIceServerTweaks.WRAPPER.info(CommandHelper.getEntityInfos(player) + TranslationHelper.get("get_painting") + id);
            }
            return 0;
        })));
    }
}
