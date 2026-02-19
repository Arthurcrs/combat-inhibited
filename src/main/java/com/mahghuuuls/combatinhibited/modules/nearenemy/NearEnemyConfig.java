package com.mahghuuuls.combatinhibited.modules.nearenemy;

import com.mahghuuuls.combatinhibited.CombatInhibited;
import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public class NearEnemyConfig {

    @Config.Comment("Should be close to an enemy apply the effect?")
    public static boolean isEnabled = true;

    @Config.Comment("Should be close to an enemy prevent the effect from expiring?")
    public static boolean preventExpiring = false;
}
