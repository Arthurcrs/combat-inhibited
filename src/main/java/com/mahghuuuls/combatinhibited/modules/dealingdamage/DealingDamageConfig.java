package com.mahghuuuls.combatinhibited.modules.dealingdamage;

import net.minecraftforge.common.config.Config;

public class DealingDamageConfig {

    @Config.Comment("Enable: apply Inhibited when the player deals damage.")
    public boolean isEnabled = true;

    @Config.Comment("Effect duration in ticks (20 ticks = 1 second).")
    public int durationTicks = 300;

    @Config.Comment("DamageSource types that should NOT trigger the effect (DamageSource#getDamageType). Example: \"player\", \"mob\".")
    public String[] damageTypeBlackList = new String[0];

    @Config.Comment("Include rule (living targets): if true, ANY living target is eligible (OR with the options below).")
    public boolean includeAll = false;

    @Config.Comment("Include rule: eligible if the target is IMob (most hostile mobs).")
    public boolean includeIMob = true;

    @Config.Comment("Include rule: eligible if the target is currently targeting any player (attack target == player).")
    public boolean includeTargetingPlayers = true;

    @Config.Comment("Exclude players as targets. If true, hitting players will NOT trigger.")
    public boolean excludePlayers = true;

    @Config.Comment("Target entity IDs that should NEVER trigger, regardless of include rules. Format: \"modid:entity_name\".")
    public String[] excludeList = new String[0];

    @Config.Comment("Target entity IDs that ALWAYS trigger the effect, overriding include/exclude rules (except damageTypeBlackList). Format: \"modid:entity_name\".")
    public String[] allowList = new String[0];
}
