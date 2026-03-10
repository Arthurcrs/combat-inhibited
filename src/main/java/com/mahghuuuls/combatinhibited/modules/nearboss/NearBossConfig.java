package com.mahghuuuls.combatinhibited.modules.nearboss;

import net.minecraftforge.common.config.Config;

public class NearBossConfig {

    @Config.Comment("Enable this module.")
    public boolean isEnabled = false;

    @Config.Comment("Scan radius in blocks.")
    public double distanceBlocks = 16.0;

    @Config.Comment("How often to scan (ticks). 20 ticks = 1 second.")
    public int scanPeriodTicks = 10;

    @Config.Comment("Duration in ticks applied/refreshed by this module.")
    public int durationTicks = 60;

    @Config.Comment("Boss entity IDs (whitelist). Only these entities are treated as bosses. Format: \"modid:entity_name\".")
    public String[] bossList = new String[] {
            "minecraft:wither",
            "minecraft:ender_dragon"
    };
}
