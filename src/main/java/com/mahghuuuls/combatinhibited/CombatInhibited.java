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
            EffectConfig DDEffectCfg = new EffectConfig(inhibitedPotion, DDConfig.durationTicks, amplifier, showParticles);
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

        //Taking Damage (TD) Module
        TakingDamageConfig TDConfig = ModConfig.takingDamageConfig;
        if (TDConfig.isEnabled) {
            EffectConfig TDEffectCfg = new EffectConfig(inhibitedPotion, TDConfig.durationTicks, amplifier, showParticles);
            EffectApplier TDApplier = new EffectApplier(TDEffectCfg);

            HashSet<String> blackListDamageTypes = new HashSet<>(Arrays.asList(TDConfig.damageTypeBlackList));

            EntityFilter TDEntityFilter = new EntityFilter();

            if (TDConfig.includeAll || TDConfig.includeIMob || TDConfig.includeTargetingPlayers) {
                TDEntityFilter.addCondition(new IsHostileCondition(TDConfig.includeAll, TDConfig.includeIMob, TDConfig.includeTargetingPlayers));
            }

            if (TDConfig.excludePlayers) {
                TDEntityFilter.addCondition(new IsNotPlayerCondition());
            }

            if (TDConfig.excludeList != null && TDConfig.excludeList.length > 0) {
                Set<String> excludeList = new HashSet<>(Arrays.asList(TDConfig.excludeList));
                TDEntityFilter.addCondition(new IsNotExcludedCondition(excludeList));
            }

            if (TDConfig.allowList != null && TDConfig.allowList.length > 0) {
                TDEntityFilter.setAllowListOverride(new HashSet<>(Arrays.asList(TDConfig.allowList)));
            }

            TakingDamageModule TDModule = new TakingDamageModule(TDApplier, blackListDamageTypes, TDEntityFilter, TDConfig.includeNonEntityDamageSources);
            MinecraftForge.EVENT_BUS.register(TDModule);
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
