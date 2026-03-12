package com.mahghuuuls.combatinhibited.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public final class SideUtil {

    private SideUtil() {}

    public static boolean isRemote(World world) {
        return world != null && world.isRemote;
    }

    public static boolean isRemote(Entity entity) {
        return entity != null && isRemote(entity.getEntityWorld());
    }

    public static boolean isServer(World world) {
        return world != null && !world.isRemote;
    }

    public static boolean isServer(Entity entity) {
        return entity != null && isServer(entity.getEntityWorld());
    }
}
