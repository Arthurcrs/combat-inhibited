package com.mahghuuuls.combatinhibited.util.entityscanner;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public interface EntityMatch {
    boolean matches(EntityPlayer player, EntityLivingBase entity, String entityId);
}
