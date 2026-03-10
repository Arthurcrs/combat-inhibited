package com.mahghuuuls.combatinhibited.util.entityscanner;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public final class NearbyEntityScanner implements EntityScanner {

    @Override
    public boolean anyMatch(EntityPlayer player, double distanceBlocks, EntityMatch match) {
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

            ResourceLocation key = EntityList.getKey(entity);
            if (key == null) continue;

            String id = key.toString();
            if (match.matches(player, entity, id)) return true;
        }

        return false;
    }
}
