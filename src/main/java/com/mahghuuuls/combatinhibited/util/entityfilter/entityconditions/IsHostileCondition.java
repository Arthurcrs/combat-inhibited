package com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions;

import com.mahghuuuls.combatinhibited.util.entityfilter.EntityCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityContext;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;

public class IsHostileCondition implements EntityCondition {

    private final boolean includeAll;
    private final boolean includeIMob;
    private final boolean includeTargetingPlayer;

    public IsHostileCondition(boolean includeAll, boolean considerImob, boolean considerTargetingPlayer) {
        this.includeAll = includeAll;
        this.includeIMob = considerImob;
        this.includeTargetingPlayer = considerTargetingPlayer;
    }

    @Override
    public boolean test(EntityContext context) {

        if (includeAll) {
            return true;
        }

        if (includeIMob && entityIsIMob(context.entity)) {
            return true;
        }

        if (includeTargetingPlayer && entityIsTargetingAnyPlayer(context.entity)) {
            return true;
        }

        return false;
    }

    private boolean entityIsIMob(EntityLivingBase entity){
        return entity instanceof IMob;
    }

    private boolean entityIsTargetingAnyPlayer(EntityLivingBase entity) {
        if (!(entity instanceof EntityLiving)) return false;
        EntityLiving living = (EntityLiving) entity;
        return living.getAttackTarget() instanceof EntityPlayer;
    }
}
