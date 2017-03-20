package com.ashindigo.frost.entities;

import com.ashindigo.frost.Frost;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityHailSphere extends EntityFireball {
	
	private EntityLivingBase user;

	public EntityHailSphere(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, EntityLivingBase shooter) {
		super(worldIn, x, y, z, accelX, accelY, accelZ);
		user = shooter;
	}

	public EntityHailSphere(World world) {
		super(world);
	}

	public EntityHailSphere(World world, EntityPlayer ep) {
		super(world);
		user = ep;
		Vec3d vec = ep.getLookVec();
		double v = 1.0;
		motionX = v*vec.xCoord;
		motionY = v*vec.yCoord;
		motionZ = v*vec.zCoord;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			if (result.entityHit != user) {
				result.entityHit.attackEntityFrom(Frost.FROSTDMG, 6F);
				this.setDead();
			}
		}
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
