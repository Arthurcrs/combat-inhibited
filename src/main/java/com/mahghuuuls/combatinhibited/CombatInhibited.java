package com.mahghuuuls.combatinhibited;

import com.mahghuuuls.combatinhibited.general.InhibitedEffectDefaults;
import com.mahghuuuls.combatinhibited.general.ModConfig;
import com.mahghuuuls.combatinhibited.modules.dealingdamage.DealingDamageModule;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectApplier;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectConfig;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

        int amplifier = InhibitedEffectDefaults.amplifier;
        boolean showParticles = InhibitedEffectDefaults.showParticles;

        if (inhibitedPotion == null) {
            throw new RuntimeException("Inhibited potion reference is null.");
        }

        if (ModConfig.dealingDamageConfig.isEnabled) {
            int dealingDuration = ModConfig.dealingDamageConfig.durationTicks;
            EffectConfig dealingCfg = new EffectConfig(inhibitedPotion, dealingDuration, amplifier, showParticles);
            EffectApplier dealingApplier = new EffectApplier(dealingCfg);

            HashSet<String> blackListDamageTypes = new HashSet<>(Arrays.asList(ModConfig.dealingDamageConfig.blackListDamageTypes));
            HashSet<String> targetBlacklist = new HashSet<>(Arrays.asList(ModConfig.dealingDamageConfig.targetBlacklist));

            DealingDamageModule dealingDamageModule = new DealingDamageModule(dealingApplier, blackListDamageTypes, targetBlacklist);

            MinecraftForge.EVENT_BUS.register(dealingDamageModule);
        }

    }
}
