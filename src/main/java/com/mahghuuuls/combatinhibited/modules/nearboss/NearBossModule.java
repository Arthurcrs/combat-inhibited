package com.mahghuuuls.combatinhibited.modules.nearboss;

import com.mahghuuuls.combatinhibited.util.effectapplier.EffectApplier;
import com.mahghuuuls.combatinhibited.util.entityscanner.EntityScanner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Set;

public final class NearBossModule {

    private final EntityScanner scanner;
    private final EffectApplier applier;
    private final Set<String> bossList;
    private final double distanceBlocks;
    private final int scanPeriodTicks;

    public NearBossModule(EntityScanner scanner,
                          EffectApplier applier,
                          Set<String> bossList,
                          double distanceBlocks,
                          int scanPeriodTicks) {
        this.scanner = scanner;
        this.applier = applier;
        this.bossList = bossList;
        this.distanceBlocks = distanceBlocks;
        this.scanPeriodTicks = Math.max(1, scanPeriodTicks);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        EntityPlayer player = event.player;
        if (player == null) return;
        if (player.world == null || player.world.isRemote) return;

        if (distanceBlocks <= 0) return;
        if ((player.ticksExisted % scanPeriodTicks) != 0) return;

        if (bossList == null || bossList.isEmpty()) return;

        boolean foundBoss = scanner.anyMatch(player, distanceBlocks, (p, e, id) -> bossList.contains(id));
        if (foundBoss) {
            applier.apply(player);
        }
    }
}
