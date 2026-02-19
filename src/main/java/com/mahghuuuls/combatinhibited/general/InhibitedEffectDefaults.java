package com.mahghuuuls.combatinhibited.general;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InhibitedEffectDefaults {
    public static final Potion inhibitedPotion = ForgeRegistries.POTIONS.getValue(new ResourceLocation("inhibited", "inhibited"));;
    public static final int amplifier = 0;
    public static final boolean showParticles = false;
    public static final boolean showIcon = GeneralConfig.showIcon;
}
