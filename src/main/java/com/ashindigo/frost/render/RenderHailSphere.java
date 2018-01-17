package com.ashindigo.frost.render;

import org.lwjgl.opengl.GL11;

import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.FrostModelManager;
import com.ashindigo.frost.entities.EntityHailSphere;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

// TODO Render OBJ of hail sphere.
public class RenderHailSphere extends Render<EntityHailSphere> {

	public RenderHailSphere(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityHailSphere entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexBuffer = tessellator.getBuffer();
		vertexBuffer.begin(GL11.GL_QUADS,  DefaultVertexFormats.POSITION);
		for (int i = 0; FrostModelManager.hailSphereB.getQuads(null, null, 0).size() > i; i++) {
			vertexBuffer.addVertexData(FrostModelManager.hailSphereB.getQuads(null, null, 0).get(i).getVertexData());
		}
		tessellator.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHailSphere entity) {
		return new ResourceLocation(FrostConstants.MODID, "textures/entity/hailsphere.png");
	}

}
