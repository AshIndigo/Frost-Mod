package com.ashindigo.frost;

import com.ashindigo.frost.entities.EntityHailSphere;
import com.ashindigo.frost.render.RenderHailSphere;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class FrostClientProxy extends FrostCommonProxy {

	public void preInit() {
		// Java 8 Magic
		OBJLoader.INSTANCE.addDomain(FrostConstants.MODID);
		RenderingRegistry.registerEntityRenderingHandler(EntityHailSphere.class, RenderHailSphere::new);
		FrostModelManager.registerModels();
	}
}
