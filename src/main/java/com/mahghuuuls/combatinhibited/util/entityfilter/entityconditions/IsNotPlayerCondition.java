package com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions;

import com.mahghuuuls.combatinhibited.util.entityfilter.EntityCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityContext;
import net.minecraft.entity.player.EntityPlayer;

public class IsNotPlayerCondition implements EntityCondition {

    @Override
    public boolean test(EntityContext context) {
        return !(context.entity instanceof EntityPlayer);
    }
}
