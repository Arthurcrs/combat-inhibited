package com.mahghuuuls.combatinhibited.modules.nearboss;

import com.mahghuuuls.combatinhibited.ModConfig;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectApplier;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Set;

public final class NearBossModule {

    private final EffectApplier applier;
    private final Set<String> considerAsBoss;

    public NearBossModule(EffectApplier applier, Set<String> considerAsBoss) {
        this.applier = applier;
        this.considerAsBoss = considerAsBoss;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        EntityPlayer player = event.player;
        if (player == null) return;

        World world = player.world;
        if (world == null || world.isRemote) return;

        if (!ModConfig.nearBossConfig.isEnabled) return;

        double r = ModConfig.nearBossConfig.distanceBlocks;
        if (r <= 0) return;

        if ((player.ticksExisted % 10) != 0) return;

        if (considerAsBoss == null || considerAsBoss.isEmpty()) return;

        for (EntityLivingBase entity : world.getEntitiesWithinAABB(
                EntityLivingBase.class,
                player.getEntityBoundingBox().grow(r)
        )) {
            if (entity == null || entity.isDead || entity == player) continue;

            ResourceLocation entityKey = EntityList.getKey(entity);

            if (entityKey == null) continue;
            if (!considerAsBoss.contains(entityKey.toString())) continue;

            applier.apply(player);
            return;
        }
    }
}