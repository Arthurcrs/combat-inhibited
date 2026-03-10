package com.mahghuuuls.combatinhibited.util.effect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EffectApplier {

    private final EffectConfig effectConfig;
    private final ApplicationSource applicationSource;

    public EffectApplier(EffectConfig effectConfig, ApplicationSource applicationSource) {
        this.effectConfig = effectConfig;
        this.applicationSource = applicationSource;
    }

    public void apply(EntityPlayer player) {

        if (player == null) return;
        if (effectConfig == null) return;
        if (effectConfig.getPotion() == null) return;

        Potion potion = effectConfig.getPotion();
        int durationTicks = effectConfig.getDurationTicks();
        int amplifier = effectConfig.getAmplifier();
        boolean showParticles = effectConfig.isShowParticles();

        if (effectConfig.getDurationTicks() <= 0) return;

        PotionEffect effect = new PotionEffect(
                potion,
                durationTicks,
                amplifier,
                false,
                showParticles
        );

        player.addPotionEffect(effect);

        EffectApplyBus.notifyApplied(player, applicationSource);
    }
}
