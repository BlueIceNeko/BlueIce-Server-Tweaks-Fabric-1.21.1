package top.blueicemiaow.blueiceservertweaks.tools;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.level.GameRules;
import top.blueicemiaow.blueiceservertweaks.mixins.GossipTypeMixin;

public class Post {
    public static void majorPositive(boolean enabled) {
        GossipTypeMixin majorPositive = (GossipTypeMixin) (Object) GossipType.MAJOR_POSITIVE;
        int value = enabled ? 100 : 20;
        majorPositive.setMax(value);
        majorPositive.setDecayPerTransfer(value);
    }

    public static void debugGamerule(ServerLevel level, boolean enabled) {
        MinecraftServer server = level.getServer();
        GameRules gamerules = level.getGameRules();
        gamerules.getRule(GameRules.RULE_DAYLIGHT).set(!enabled, server);
        gamerules.getRule(GameRules.RULE_WEATHER_CYCLE).set(!enabled, server);
        gamerules.getRule(GameRules.RULE_KEEPINVENTORY).set(enabled, server);
        gamerules.getRule(GameRules.RULE_SPAWN_RADIUS).set(enabled ? 0 : 10, server);
        if (enabled) {
            level.setDayTime(6000);
            level.setWeatherParameters(180000, 0, false, false);
            server.setDifficulty(Difficulty.HARD, true);
        }
    }
}
