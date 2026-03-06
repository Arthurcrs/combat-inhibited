package com.mahghuuuls.combatinhibited;

import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageModule;
import com.mahghuuuls.combatinhibited.modules.nearboss.NearBossConfig;
import com.mahghuuuls.combatinhibited.modules.nearboss.NearBossModule;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageConfig;
import com.mahghuuuls.combatinhibited.modules.takingdamage.TakingDamageModule;
import com.mahghuuuls.combatinhibited.util.effect.EffectApplier;
import com.mahghuuuls.combatinhibited.util.effect.EffectConfig;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityFilter;
import com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions.IsNotPlayerCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions.IsNotExcludedCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions.IsHostileCondition;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;

@Mod(modid = CombatInhibited.MOD_ID, name = CombatInhibited.NAME, version = CombatInhibited.VERSION, dependencies = CombatInhibited.DEPENDENCIES)
public class CombatInhibited {
	public static final String MOD_ID = "combatinhibited";
	public static final String NAME = "Combat Inhibited";
	public static final String VERSION = "1.0.0";
	public static final String DEPENDENCIES = "required-after:inhibited";

	@EventHandler
	public void init(FMLInitializationEvent event) {

        Potion inhibitedPotion = ForgeRegistries.POTIONS.getValue(new ResourceLocation("inhibited", "inhibited"));

        if (inhibitedPotion == null) {
            throw new RuntimeException("Inhibited potion reference is null.");
        }

        int amplifier = 0;
        boolean showParticles = false;

        //Dealing Damage (DD) Module
        DealingDamageConfig DDConfig = ModConfig.dealingDamageConfig;
        if (DDConfig.isEnabled) {
            int dealingDuration = DDConfig.durationTicks;

            EffectConfig DDEffectCfg = new EffectConfig(inhibitedPotion, dealingDuration, amplifier, showParticles);
            EffectApplier DDApplier = new EffectApplier(DDEffectCfg);

            HashSet<String> blackListDamageTypes = new HashSet<>(Arrays.asList(DDConfig.damageTypeBlackList));

            EntityFilter DDEntityFilter = new EntityFilter();

            if (DDConfig.includeAll || DDConfig.includeIMob || DDConfig.includeTargetingPlayers) {
                DDEntityFilter.addCondition(new IsHostileCondition(DDConfig.includeAll, DDConfig.includeIMob, DDConfig.includeTargetingPlayers));
            }

            if (DDConfig.excludePlayers) {
                DDEntityFilter.addCondition(new IsNotPlayerCondition());
            }

            if (DDConfig.excludeList != null && DDConfig.excludeList.length > 0) {
                Set<String> excludeList = new HashSet<>(Arrays.asList(DDConfig.excludeList));
                DDEntityFilter.addCondition(new IsNotExcludedCondition(excludeList));
            }

            if (DDConfig.allowList != null && DDConfig.allowList.length > 0) {
                DDEntityFilter.setAllowListOverride(new HashSet<>(Arrays.asList(DDConfig.allowList)));
            }

            DealingDamageModule DDModule = new DealingDamageModule(DDApplier, blackListDamageTypes, DDEntityFilter);
            MinecraftForge.EVENT_BUS.register(DDModule);
        }

        //Taking Damage Module
        TakingDamageConfig takingDamageConfig = ModConfig.takingDamageConfig;
        if (takingDamageConfig.isEnabled) {
            int takingDuration = takingDamageConfig.durationTicks;

            EffectConfig takingDamageEffectCfg = new EffectConfig(inhibitedPotion, takingDuration, amplifier, showParticles);
            EffectApplier takingDamageApplier = new EffectApplier(takingDamageEffectCfg);

            HashSet<String> takeBlacklistTypes = new HashSet<>(Arrays.asList(takingDamageConfig.damageTypeBlackList));
            HashSet<String> attackerBlacklist = new HashSet<>(Arrays.asList(takingDamageConfig.entityBlackList));

            TakingDamageModule takingDamageModule = new TakingDamageModule(takingDamageApplier, takeBlacklistTypes, attackerBlacklist);

            MinecraftForge.EVENT_BUS.register(takingDamageModule);
        }

        //Near Boss Config
        NearBossConfig nearBossConfig = ModConfig.nearBossConfig;
        if (nearBossConfig.isEnabled) {
            EffectConfig nearBossEffectCfg = new EffectConfig(inhibitedPotion, nearBossConfig.durationTicks, amplifier, showParticles);
            EffectApplier nearBossApplier = new EffectApplier(nearBossEffectCfg);

            HashSet<String> considerAsBoss = new HashSet<>(Arrays.asList(nearBossConfig.considerAsBoss));

            int scanPeriodTicks = max(1, nearBossConfig.durationTicks - 1);

            MinecraftForge.EVENT_BUS.register(new NearBossModule(nearBossApplier, considerAsBoss, scanPeriodTicks));
        }
    }
}
