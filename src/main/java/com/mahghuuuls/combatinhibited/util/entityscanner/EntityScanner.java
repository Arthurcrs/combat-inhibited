package com.mahghuuuls.combatinhibited.util.entityscanner;

import net.minecraft.entity.player.EntityPlayer;

public interface EntityScanner {
    boolean anyMatch(EntityPlayer player, double distanceBlocks, EntityMatch match);
}
