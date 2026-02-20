package com.mahghuuuls.combatinhibited.modules.nearboss;

import com.mahghuuuls.combatinhibited.ModConfig;
import com.mahghuuuls.combatinhibited.util.EffectApplier;
import com.mahghuuuls.combatinhibited.util.EntityMatch;
import com.mahghuuuls.combatinhibited.util.NearbyEntityScanner;
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
    private final int scanPeriodTicks;

    public NearBossModule(EffectApplier applier, Set<String> considerAsBoss, int scanPeriodTicks) {
        this.applier = applier;
        this.considerAsBoss = considerAsBoss;
        if (scanPeriodTicks > 0) {
            this.scanPeriodTicks = scanPeriodTicks;
        } else {
            this.scanPeriodTicks = 1;
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        EntityPlayer player = event.player;
        if (player == null) return;

        World world = player.world;
        if (world == null || world.isRemote) return;

        if (!ModConfig.nearBossConfig.isEnabled) return;

        double distanceBlocks = ModConfig.nearBossConfig.distanceBlocks;
        if (distanceBlocks <= 0) return;

        if ((player.ticksExisted % scanPeriodTicks) != 0) return;

        if (considerAsBoss == null || considerAsBoss.isEmpty()) return;

        if (NearbyEntityScanner.anyMatch(player, distanceBlocks, new EntityMatch() {
            @Override public boolean matches(EntityLivingBase entity, String id) {
                return considerAsBoss.contains(id);
            }
        })) {
            applier.apply(player);
        }
    }
}