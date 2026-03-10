package com.mahghuuuls.combatinhibited.modules.dealingdamage;

import com.mahghuuuls.combatinhibited.util.EntityUtils;
import com.mahghuuuls.combatinhibited.util.effect.EffectApplier;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityContext;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityFilter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

public final class DealingDamageModule {

    private final EffectApplier applier;
    private final Set<String> blacklistDamageTypes;
    private final EntityFilter entityFilter;

    public DealingDamageModule(EffectApplier applier,
                               Set<String> blacklistDamageTypes,
                               EntityFilter entityFilter) {
        this.applier = applier;
        this.blacklistDamageTypes = blacklistDamageTypes;
        this.entityFilter = entityFilter;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().world.isRemote) return;

        DamageSource damageSource = event.getSource();
        if (damageSource == null) return;
        String damageType = damageSource.getDamageType();
        if (blacklistDamageTypes != null && blacklistDamageTypes.contains(damageType)) return;

        Entity attacker = damageSource.getTrueSource();
        if (!(attacker instanceof EntityPlayer)) return;
        EntityLivingBase target = event.getEntityLiving();
        String targetEntityId = EntityUtils.getEntityId(target);

        EntityContext context = new EntityContext((EntityPlayer) attacker, target, targetEntityId);

        if (entityFilter == null || entityFilter.passes(context)) {
            applier.apply((EntityPlayer) attacker);
        }
    }
}
