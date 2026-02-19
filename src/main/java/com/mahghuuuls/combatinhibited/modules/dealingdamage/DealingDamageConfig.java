package com.mahghuuuls.combatinhibited.modules.dealingdamage;

import com.mahghuuuls.combatinhibited.CombatInhibited;

import net.minecraftforge.common.config.Config;

@Config(modid = CombatInhibited.MOD_ID)
public class DealingDamageConfig {

	@Config.Comment("Should dealing damage apply the effect?")
	public static boolean isEnabled = true;
}
