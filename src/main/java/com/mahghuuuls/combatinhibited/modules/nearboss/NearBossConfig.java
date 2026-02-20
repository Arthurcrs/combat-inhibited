package com.mahghuuuls.combatinhibited.modules.nearboss;

import net.minecraftforge.common.config.Config;

public class NearBossConfig {

    @Config.Comment("Should being close to a configured boss apply the effect?")
    public boolean isEnabled = true;

    @Config.Comment("Distance (blocks) from a boss to apply the effect.")
    public double distanceBlocks = 80.0;

    @Config.Comment("How often refresh the effect when close to a configured boss.")
    public int durationTicks = 300;

    @Config.Comment("Entity IDs treated as bosses.")
    public String[] considerAsBoss = new String[] {
            "minecraft:wither",
            "minecraft:ender_dragon"
    };
}
