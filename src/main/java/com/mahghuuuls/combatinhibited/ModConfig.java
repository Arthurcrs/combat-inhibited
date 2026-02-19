package com.mahghuuuls.combatinhibited;

import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.nearboss.NearBossConfig;
import com.mahghuuuls.combatinhibited.modules.nearenemy.NearEnemyConfig;
import com.mahghuuuls.combatinhibited.modules.preventexpiring.PreventExpiringConfig;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public final class ModConfig {

    @Config.Name("Dealing damage")
    @Config.Comment("Module settings")
    public static final DealingDamageConfig dealingDamageConfig = new DealingDamageConfig();

    @Config.Name("Taking damage")
    @Config.Comment("Module settings")
    public static final TakingDamageConfig takingDamageConfig = new TakingDamageConfig();

    @Config.Name("Being near a boss")
    @Config.Comment("Module settings")
    public static final NearBossConfig nearBossConfig = new NearBossConfig();

    @Config.Name("Being near a enemy")
    @Config.Comment("Module settings")
    public static final NearEnemyConfig nearEnemyConfig = new NearEnemyConfig();

    @Config.Name("Prevent expiring when near enemy")
    @Config.Comment("Module settings")
    public static final PreventExpiringConfig preventExpiringConfig = new PreventExpiringConfig();
}
