package com.mahghuuuls.combatinhibited.modules.nearenemy;

import net.minecraftforge.common.config.Config;

public class NearEnemyConfig {

    @Config.Comment("Should be close to an enemy apply the effect?")
    public boolean isEnabled = true;

    @Config.Comment("0: close to an enemy apply the effect or 1 should being close just prevent the effect from expiring?")
    public int mode = 0;

}
