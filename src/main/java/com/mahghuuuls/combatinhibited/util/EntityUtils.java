package com.mahghuuuls.combatinhibited.util;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class EntityUtils {

    public static ResourceLocation getEntityKey(EntityLivingBase entity){
        return EntityList.getKey(entity);
    }

    public static String getEntityId(EntityLivingBase entity){
        ResourceLocation entityKey = getEntityKey(entity);
        return entityKey.toString();
    }
}
