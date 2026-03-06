package com.mahghuuuls.combatinhibited.modules.dealingdamage;

import net.minecraftforge.common.config.Config;

public class DealingDamageConfig {

    //TODO: Rename "the effect" to something like Inhibited

    @Config.Comment("Should dealing damage apply the effect?")
    public boolean isEnabled = true;

    @Config.Comment("Duration in ticks of the effect when a player deals damage")
    public int durationTicks = 300;

    @Config.Comment("Damage types that should NOT trigger the effect")
    public String[] damageTypeBlackList = new String[0];

    @Config.Comment("Should dealing damage to any entity trigger the effect?")
    public boolean includeAll = false;

    @Config.Comment("Should dealing damage to IMob entities (usually hostile entities) trigger the effect?")
    public boolean includeIMob = true;

    @Config.Comment("Should dealing damage to entities that are targeting a player trigger the effect?")
    public boolean includeTargetingPlayers = true;

    @Config.Comment("Should dealing damage to players not trigger the effect?")
    public boolean excludePlayers = true;

    @Config.Comment("Target entity IDs that should NOT trigger the effect regardless of other conditions.")
    public String[] excludeList = new String[0];

    @Config.Comment("Target entity IDs that should ALWAYS trigger the effect regardless of other conditions.")
    public String[] allowList = new String[0];
}
