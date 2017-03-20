package com.ashindigo.frost;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

public class FrostModelManager {
	
	public static IBakedModel hailSphereB;

	public static void registerModels() {
		try {
			//OBJLoader.INSTANCE.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
			IModel model = OBJLoader.INSTANCE.loadModel(new ResourceLocation("frost:entity/hailsphere.obj"));
			hailSphereB = model.bake(model.getDefaultState(), new VertexFormat(), ModelLoader.defaultTextureGetter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
