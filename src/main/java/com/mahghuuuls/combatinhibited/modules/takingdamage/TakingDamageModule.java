package com.mahghuuuls.combatinhibited.modules.takingdamage;

import com.mahghuuuls.combatinhibited.util.EffectApplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityList;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

public final class TakingDamageModule {

    private final EffectApplier applier;
    private final Set<String> blacklistDamageTypes;
    private final Set<String> attackerBlacklistEntityIds;

    public TakingDamageModule(EffectApplier applier,
                              Set<String> blacklistDamageTypes,
                              Set<String> attackerBlacklistEntityIds) {
        this.applier = applier;
        this.blacklistDamageTypes = blacklistDamageTypes;
        this.attackerBlacklistEntityIds = attackerBlacklistEntityIds;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().world.isRemote) return;

        EntityLivingBase victim = event.getEntityLiving();
        if (!(victim instanceof EntityPlayer)) return;

        DamageSource damageSource = event.getSource();
        if (damageSource == null) return;

        String damageType = damageSource.getDamageType();
        if (blacklistDamageTypes != null && blacklistDamageTypes.contains(damageType)) {
            return;
        }

        Entity attacker = damageSource.getTrueSource();
        if (attacker != null && attackerBlacklistEntityIds != null && !attackerBlacklistEntityIds.isEmpty()) {
            ResourceLocation key = EntityList.getKey(attacker);
            if (key != null && attackerBlacklistEntityIds.contains(key.toString())) {
                return;
            }
        }

        applier.apply((EntityPlayer) victim);
    }
}
