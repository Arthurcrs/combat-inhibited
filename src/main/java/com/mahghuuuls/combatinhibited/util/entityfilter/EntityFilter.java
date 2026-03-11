package com.mahghuuuls.combatinhibited.util.entityfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class EntityFilter {
    private Set<String> allowListOverride;
    private final List<EntityCondition> conditions = new ArrayList<>();

    public EntityFilter(){}

    public boolean passes(EntityContext context) {

        if (context == null) return false;

        if (context.entityId != null
                && allowListOverride != null
                && !allowListOverride.isEmpty()
                && allowListOverride.contains(context.entityId)) {
            return true;
        }

        for (EntityCondition condition : conditions) {
            if (condition == null) continue;
            if (!condition.test(context)) return false;
        }

        return true;
    }

    public void addCondition(EntityCondition condition) {
        if (condition != null) conditions.add(condition);
    }

    public void setAllowListOverride(Set<String> allowListOverride) {
        this.allowListOverride = allowListOverride;
    }
}
