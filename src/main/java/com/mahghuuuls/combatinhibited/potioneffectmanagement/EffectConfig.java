package com.mahghuuuls.combatinhibited.potioneffectmanagement;

import net.minecraft.potion.Potion;

public class EffectConfig {

    private final Potion potion;
    private final int durationTicks;
    private final int amplifier;
    private final boolean showParticles;

    public EffectConfig(
            Potion potion,
            int durationTicks,
            int amplifier,
            boolean showParticles
    ){
        this.potion = potion;
        this.durationTicks = durationTicks;
        this.amplifier = amplifier;
        this.showParticles = showParticles;
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
}
