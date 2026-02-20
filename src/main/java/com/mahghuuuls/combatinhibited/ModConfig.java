package com.mahghuuuls.combatinhibited;

import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.nearboss.NearBossConfig;
import com.mahghuuuls.combatinhibited.modules.nearenemy.NearEnemyConfig;
import com.mahghuuuls.combatinhibited.modules.preventexpiring.PreventExpiringConfig;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public final class ModConfig {

    @Config.Name("dealing_damage")
    @Config.Comment("Applies Inhibited when the player deals damage.")
    public static final DealingDamageConfig dealingDamageConfig = new DealingDamageConfig();

    @Config.Name("taking_damage")
    @Config.Comment("Applies Inhibited when the player takes damage.")
    public static final TakingDamageConfig takingDamageConfig = new TakingDamageConfig();

    @Config.Name("near_boss")
    @Config.Comment("Applies Inhibited while the player is within range of a configured boss entity.")
    public static final NearBossConfig nearBossConfig = new NearBossConfig();

    @Config.Name("near_enemy")
    @Config.Comment("Applies Inhibited while the player is within range of a configured enemy entity.")
    public static final NearEnemyConfig nearEnemyConfig = new NearEnemyConfig();

    @Config.Name("prevent_expiring")
    @Config.Comment("Prevents the Inhibited effect from expiring under configured conditions.")
    public static final PreventExpiringConfig preventExpiringConfig = new PreventExpiringConfig();
}
