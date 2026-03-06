package com.mahghuuuls.combatinhibited.util.entityfilter.entityconditions;

import com.mahghuuuls.combatinhibited.util.entityfilter.EntityCondition;
import com.mahghuuuls.combatinhibited.util.entityfilter.EntityContext;

import java.util.Set;

public class IsNotExcludedCondition implements EntityCondition {

    private final Set<String> excludeList;

    public IsNotExcludedCondition(Set<String> excludeList) {
        this.excludeList = excludeList;
    }

    @Override
    public boolean test(EntityContext context) {
        if (excludeList == null || excludeList.isEmpty()) return true;
        if (context == null || context.entityId == null) return true;

        return !excludeList.contains(context.entityId);
    }
}
