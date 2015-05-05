package com.enderio.core.client.render;

import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.GroupObject;

import com.enderio.core.api.client.render.VertexTransform;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TechneModelRenderer implements ISimpleBlockRenderingHandler {

  private Collection<GroupObject> model;
  private int renderId;

  protected VertexTransform vt;
  
  public TechneModelRenderer(String modid, String modelPath, int renderId) {
    this(modid, modelPath, renderId, null);
  }

  public TechneModelRenderer(String modid, String modelPath, int renderId, VertexTransform vt) {
    this(TechneUtil.getModelAll(modid, modelPath), renderId, vt);
  }
  
  public TechneModelRenderer(Collection<GroupObject> model, int renderId) {
    this(model, renderId, null);
  }

  public TechneModelRenderer(Collection<GroupObject> model, int renderId, VertexTransform vt) {
    this.model = model;
    this.renderId = renderId;
    this.vt = vt;
  }

  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    TechneUtil.vt = this.vt;
    TechneUtil.renderInventoryBlock(model, getModelIcon(block, metadata), block, metadata, renderer);
  }

  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    TechneUtil.vt = this.vt;
    return TechneUtil.renderWorldBlock(model, getModelIcon(world, x, y, z, block), world, x, y, z, block, renderer);
  }
  
  protected IIcon getModelIcon(Block block, int metadata) {
    return block.getIcon(0, metadata);
  }

  protected IIcon getModelIcon(IBlockAccess world, int x, int y, int z, Block block) {
    return block.getIcon(world, x, y, z, 0);
  }

  @Override
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }

  @Override
  public int getRenderId() {
    return renderId;
  }
}