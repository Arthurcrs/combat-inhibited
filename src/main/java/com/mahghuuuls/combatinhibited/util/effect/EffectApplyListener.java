package com.mahghuuuls.combatinhibited.util.effect;

import net.minecraft.entity.player.EntityPlayer;

public interface EffectApplyListener {
    void onApplied(EntityPlayer player, ApplicationSource source);
}
