package com.mahghuuuls.combatinhibited.util;

import net.minecraft.entity.EntityLivingBase;

public interface EntityMatch {
    boolean matches(EntityLivingBase entity, String entityId);
}
