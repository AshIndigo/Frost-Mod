package com.ashindigo.frost;

import com.ashindigo.frost.entities.EntityHailSphere;
import com.ashindigo.frost.render.RenderHailSphere;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class FrostClientProxy extends FrostCommonProxy {

	public void preInit() {
		// Java 8 Magic
		OBJLoader.INSTANCE.addDomain(FrostConstants.MODID);
		RenderingRegistry.registerEntityRenderingHandler(EntityHailSphere.class, RenderHailSphere::new);
		FrostModelManager.registerModels();
	}
	
	public void init() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(FrostBlocks.frozenTable), 0, new ModelResourceLocation(FrostBlocks.frozenTable.getRegistryName(), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(FrostBlocks.trickIce), 0, new ModelResourceLocation(FrostBlocks.trickIce.getRegistryName(), "inventory"));
	}
}
