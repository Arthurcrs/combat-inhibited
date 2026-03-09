package com.mahghuuuls.combatinhibited.modules.takingdamage;

import net.minecraftforge.common.config.Config;

public class TakingDamageConfig {

    @Config.Comment("Enable: apply Inhibited when the player takes damage.")
    public boolean isEnabled = true;

    @Config.Comment("Effect duration in ticks (20 ticks = 1 second).")
    public int durationTicks = 300;

    @Config.Comment("DamageSource types that should NOT trigger the effect (DamageSource#getDamageType). Example: \"fall\", \"lava\".")
    public String[] damageTypeBlackList = new String[0];

    @Config.Comment("If true, damage with NO living attacker (fall, lava, cactus, etc.) can trigger the effect. If false, only damage caused by a living entity can trigger.")
    public boolean includeNonEntityDamageSources = true;

    @Config.Comment("Include rule (living attackers): if true, ANY living attacker is eligible (OR with the options below).")
    public boolean includeAll = true;

    @Config.Comment("Include rule: eligible if the attacker is IMob (most hostile mobs).")
    public boolean includeIMob = false;

    @Config.Comment("Include rule: eligible if the attacker is currently targeting any player (attack target == player).")
    public boolean includeTargetingPlayers = false;

    @Config.Comment("Exclude players as attackers. If true, damage caused by players will NOT trigger.")
    public boolean excludePlayers = true;

    @Config.Comment("Attacker entity IDs that should NEVER trigger, regardless of include rules. Format: \"modid:entity_name\" (e.g., \"minecraft:zombie\").")
    public String[] excludeList = new String[0];

    @Config.Comment("Attacker entity IDs that ALWAYS trigger the effect, overriding include/exclude rules (except damageTypeBlackList). Format: \"modid:entity_name\".")
    public String[] allowList = new String[0];
}
