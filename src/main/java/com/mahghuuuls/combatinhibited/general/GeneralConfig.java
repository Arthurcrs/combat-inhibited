package com.mahghuuuls.combatinhibited.general;

import com.mahghuuuls.combatinhibited.CombatInhibited;

import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public class GeneralConfig {

	@Config.Comment("Display inhibited icon?")
    public static boolean showIcon = false;

}
