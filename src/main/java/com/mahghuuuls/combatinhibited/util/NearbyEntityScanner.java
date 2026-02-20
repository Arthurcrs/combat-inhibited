package com.mahghuuuls.combatinhibited.util;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public final class NearbyEntityScanner {

    private NearbyEntityScanner() {}

    public static boolean anyMatch(EntityPlayer player, double distanceBlocks, EntityMatch match) {
        if (player == null || match == null) return false;

        World world = player.world;
        if (world == null) return false;

        if (distanceBlocks <= 0) return false;

        List<EntityLivingBase> list = world.getEntitiesWithinAABB(
                EntityLivingBase.class,
                player.getEntityBoundingBox().grow(distanceBlocks)
        );

        for (EntityLivingBase entity : list) {
            if (entity == null || entity.isDead || entity == player) continue;

            ResourceLocation entityKey = EntityList.getKey(entity);
            if (entityKey == null) continue;

            String id = entityKey.toString();
            if (match.matches(entity, id)) return true;
        }

        return false;
    }
}
