package com.mahghuuuls.combatinhibited;

import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageModule;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageModule;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectApplier;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectConfig;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;

@Mod(modid = CombatInhibited.MOD_ID, name = CombatInhibited.NAME, version = CombatInhibited.VERSION, dependencies = CombatInhibited.DEPENDENCIES)
public class CombatInhibited {
	public static final String MOD_ID = "combatinhibited";
	public static final String NAME = "Combat Inhibited";
	public static final String VERSION = "1.0.0";
	public static final String DEPENDENCIES = "required-after:inhibited";

	@EventHandler
	public void init(FMLInitializationEvent event) {

        Potion inhibitedPotion = ForgeRegistries.POTIONS.getValue(new ResourceLocation("inhibited", "inhibited"));
        int amplifier = 0;
        boolean showParticles = false;

        if (inhibitedPotion == null) {
            throw new RuntimeException("Inhibited potion reference is null.");
        }

        //Dealing Damage Module
        DealingDamageConfig dealingDamageConfig = ModConfig.dealingDamageConfig;
        if (dealingDamageConfig.isEnabled) {
            int dealingDuration = dealingDamageConfig.durationTicks;

            EffectConfig dealingCfg = new EffectConfig(inhibitedPotion, dealingDuration, amplifier, showParticles);
            EffectApplier dealingApplier = new EffectApplier(dealingCfg);

            HashSet<String> blackListDamageTypes = new HashSet<>(Arrays.asList(dealingDamageConfig.damageTypeBlackList));
            HashSet<String> targetBlacklist = new HashSet<>(Arrays.asList(dealingDamageConfig.entityBlackList));

            DealingDamageModule dealingDamageModule = new DealingDamageModule(dealingApplier, blackListDamageTypes, targetBlacklist);

            MinecraftForge.EVENT_BUS.register(dealingDamageModule);
        }

        //Taking Damage Module
        TakingDamageConfig takingDamageConfig = ModConfig.takingDamageConfig;
        if (takingDamageConfig.isEnabled) {
            int takingDuration = takingDamageConfig.durationTicks;

            EffectConfig takingCfg = new EffectConfig(inhibitedPotion, takingDuration, amplifier, showParticles);
            EffectApplier takingApplier = new EffectApplier(takingCfg);

            HashSet<String> takeBlacklistTypes = new HashSet<>(Arrays.asList(takingDamageConfig.damageTypeBlackList));
            HashSet<String> attackerBlacklist = new HashSet<>(Arrays.asList(takingDamageConfig.entityBlackList));

            TakingDamageModule takingDamageModule = new TakingDamageModule(takingApplier, takeBlacklistTypes, attackerBlacklist);

            MinecraftForge.EVENT_BUS.register(takingDamageModule);
        }
    }
}
