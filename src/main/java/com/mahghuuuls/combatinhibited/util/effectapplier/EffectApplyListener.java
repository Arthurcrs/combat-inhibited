package com.mahghuuuls.combatinhibited.util.effectapplier;

import com.mahghuuuls.combatinhibited.util.reaplicationlimiter.ApplicationSource;
import net.minecraft.entity.player.EntityPlayer;

public interface EffectApplyListener {
    void onApplied(EntityPlayer player, ApplicationSource source);
}
