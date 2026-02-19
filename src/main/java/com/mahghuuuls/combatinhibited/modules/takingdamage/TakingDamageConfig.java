package com.mahghuuuls.combatinhibited.modules.takingdamage;

import net.minecraftforge.common.config.Config;

public class TakingDamageConfig {

    @Config.Comment("Should taking damage apply the effect?")
    public boolean isEnabled = true;

    @Config.Comment("Duration of the effect when a player takes damage")
    public int durationTicks = 300;

    @Config.Comment("Damage types that should NOT trigger the effect")
    public String[] blackListDamageTypes = new String[0];

    @Config.Comment("Target entity IDs that should NOT trigger the effect (e.g., minecraft:zombie).")
    public String[] targetBlacklist = new String[0];

}
