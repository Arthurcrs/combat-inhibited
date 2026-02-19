package com.mahghuuuls.combatinhibited.modules.nearboss;

import com.mahghuuuls.combatinhibited.CombatInhibited;
import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public class NearBossConfig {

    @Config.Comment("Should be close to a boss apply the effect?")
    public static boolean isEnabled = true;

    @Config.Comment("Should be close to a boss prevent the effect from expiring?")
    public static boolean preventExpiring = true;
}
