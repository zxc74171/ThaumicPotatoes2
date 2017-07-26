package com.zxc74171.thaumicpotatoes.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public final class EntityUtil {

  private EntityUtil() {
  }

  public static RayTraceResult raytraceEntityPlayerLook(EntityPlayer player, float range) {
    Vec3d eye = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ); 
    Vec3d look = player.getLook(1.0f);

    return raytraceEntity(player, eye, look, range, true);
  }


  public static RayTraceResult raytraceEntity(Entity entity, Vec3d start, Vec3d look, double range, boolean ignoreCanBeCollidedWith) {

    Vec3d direction = start.addVector(look.x * range, look.y * range, look.z * range);

    Entity pointedEntity = null;
    Vec3d hit = null;
    AxisAlignedBB bb = entity.getEntityBoundingBox().expand(look.x * range, look.y * range, look.z * range).expand(1, 1, 1);
    List<Entity> entitiesInArea = entity.getEntityWorld().getEntitiesWithinAABBExcludingEntity(entity, bb);
    double range2 = range; 

    for(Entity candidate : entitiesInArea) {
      if(ignoreCanBeCollidedWith || candidate.canBeCollidedWith()) {
        double colBorder = candidate.getCollisionBorderSize();
        AxisAlignedBB entityBB = candidate.getEntityBoundingBox().expand(colBorder, colBorder, colBorder);
        RayTraceResult movingobjectposition = entityBB.calculateIntercept(start, direction);

        if(entityBB.contains(start)) {
          if(0.0D < range2 || range2 == 0.0D) {
            pointedEntity = candidate;
            hit = movingobjectposition == null ? start : movingobjectposition.hitVec;
            range2 = 0.0D;
          }
        }
        else if(movingobjectposition != null) {
          double dist = start.distanceTo(movingobjectposition.hitVec);

          if(dist < range2 || range2 == 0.0D) {
            if(candidate == entity.getRidingEntity() && !entity.canRiderInteract()) {
              if(range2 == 0.0D) {
                pointedEntity = candidate;
                hit = movingobjectposition.hitVec;
              }
            }
            else {
              pointedEntity = candidate;
              hit = movingobjectposition.hitVec;
              range2 = dist;
            }
          }
        }
      }
    }

    if(pointedEntity != null && range2 < range) {
      return new RayTraceResult(pointedEntity, hit);
    }
    return null;
  }
}