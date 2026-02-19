package com.mahghuuuls.combatinhibited.potioneffectmanagement;

import com.mahghuuuls.combatinhibited.general.InhibitedEffectDefaults;
import net.minecraft.potion.Potion;

public class EffectConfig {

    private final Potion potion = InhibitedEffectDefaults.inhibitedPotion;
    private final int durationTicks;
    private final int amplifier;
    private final boolean showParticles;
    private final boolean showIcon;

    public EffectConfig(
            int durationTicks
    ){
        this.durationTicks = durationTicks;
        this.amplifier = InhibitedEffectDefaults.amplifier;
        this.showParticles = InhibitedEffectDefaults.showParticles;
        this.showIcon = InhibitedEffectDefaults.showIcon;
    }

    public Potion getPotion(){
        return potion;
    }

    public int getDurationTicks() {
        return durationTicks;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isShowParticles() {
        return showParticles;
    }

    public boolean isShowIcon() {
        return showIcon;
    }
}
