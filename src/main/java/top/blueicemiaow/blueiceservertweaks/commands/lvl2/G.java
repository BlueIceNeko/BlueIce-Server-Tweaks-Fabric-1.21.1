package top.blueicemiaow.blueiceservertweaks.commands.lvl2;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class G {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("g").requires(s -> s.hasPermission(2)).then(Commands.argument("item", ItemArgument.item(commandBuildContext)).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player) {
                ItemInput item = ItemArgument.getItem(arguments, "item");
                player.getInventory().add(item.createItemStack(new ItemStack(item.getItem()).getMaxStackSize(), false));
                BlueIceServerTweaks.WRAPPER.info(CommandHelper.getEntityInfos(player) + TranslationHelper.get("g") + item.serialize(arguments.getSource().registryAccess()));
            }
            return 0;
        })));
    }
}
