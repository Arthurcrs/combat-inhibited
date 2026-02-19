package com.mahghuuuls.combatinhibited.modules.dealingdamage;

import com.mahghuuuls.combatinhibited.general.ModConfig;
import com.mahghuuuls.combatinhibited.potioneffectmanagement.EffectApplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityList;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

public final class DealingDamageModule {

    private final EffectApplier applier;
    private final Set<String> blacklistDamageTypes;
    private final Set<String> blacklistEntityIds;

    public DealingDamageModule(EffectApplier applier,
                               Set<String> blacklistDamageTypes,
                               Set<String> blacklistEntityIds) {
        this.applier = applier;
        this.blacklistDamageTypes = blacklistDamageTypes;
        this.blacklistEntityIds = blacklistEntityIds;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().world.isRemote) return;

        DamageSource damageSource = event.getSource();
        if (damageSource == null) return;

        Entity attacker = damageSource.getTrueSource();
        if (!(attacker instanceof EntityPlayer)) return;

        String damageType = damageSource.getDamageType();
        if (blacklistDamageTypes != null && blacklistDamageTypes.contains(damageType)) {
            return;
        }

        EntityLivingBase target = event.getEntityLiving();
        if (blacklistEntityIds != null && !blacklistEntityIds.isEmpty()) {
            ResourceLocation key = EntityList.getKey(target);
            if (key != null && blacklistEntityIds.contains(key.toString())) {
                return;
            }
        }

        applier.apply((EntityPlayer) attacker);
    }
}