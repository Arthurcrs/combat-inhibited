package com.mahghuuuls.combatinhibited.util.entityfilter;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public final class EntityContext {
    public final EntityPlayer player;
    public final EntityLivingBase entity;
    public final String entityId; // "minecraft:zombie"

    public EntityContext(EntityPlayer player, EntityLivingBase entity, String entityId) {
        this.player = player;
        this.entity = entity;
        this.entityId = entityId;
    }

}
