package com.mahghuuuls.combatinhibited.modules.takingdamage;

import com.mahghuuuls.combatinhibited.CombatInhibited;
import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public class TakingDamageConfig {

    @Config.Comment("Should taking damage apply the effect?")
    public static boolean isEnabled = true;

}
