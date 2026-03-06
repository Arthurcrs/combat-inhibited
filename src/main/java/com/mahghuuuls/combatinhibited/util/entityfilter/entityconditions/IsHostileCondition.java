package com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions;

import com.mahghuuuls.combatinhibited.util.entityfilter.EntityCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityContext;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;

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

        if (includeIMob && entityIsImob(context)) {
            return true;
        }

        if (includeTargetingPlayer && entityIsTargetingPlayer(context)) {
            return true;
        }

        return false;
    }

    private boolean entityIsImob(EntityContext context){
        return context.entity instanceof IMob;
    }

    private boolean entityIsTargetingPlayer(EntityContext context){
        if (!(context.entity instanceof EntityLiving)) return false;
        EntityLiving living = (EntityLiving) context.entity;
        return living.getAttackTarget() == context.player;
    }
}
