package com.enderio.core.client.render;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.enderio.core.EnderCore;

import com.sun.prism.TextureMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IconUtil {

  public static interface IIconProvider {

    public void registerIcons(@Nonnull AtlasTexture register);

  }

  public static @Nonnull IconUtil instance = new IconUtil();

  public static void addIconProvider(@Nonnull IIconProvider registrar) {
    instance.iconProviders.add(registrar);
  }

  private final @Nonnull ArrayList<IIconProvider> iconProviders = new ArrayList<IIconProvider>();

  public @Nonnull TextureAtlasSprite whiteTexture;
  public @Nonnull TextureAtlasSprite blankTexture;
  public @Nonnull TextureAtlasSprite errorTexture;

  private boolean doneInit = false;

  @SuppressWarnings("null")
  private IconUtil() {
  }

  public void init() {
    if (doneInit) {
      return;
    }
    doneInit = true;
    MinecraftForge.EVENT_BUS.register(this);
    addIconProvider(new IIconProvider() {

      @Override
      public void registerIcons(@Nonnull AtlasTexture register) {

        whiteTexture = register.registerSprite(new ResourceLocation(EnderCore.MODID, "white"));
        errorTexture = register.registerSprite(new ResourceLocation(EnderCore.MODID, "error"));
        blankTexture = register.registerSprite(new ResourceLocation(EnderCore.MODID, "blank"));
      }

    });
  }

  @SubscribeEvent
  public void onIconLoad(TextureStitchEvent.Pre event) {
    for (IIconProvider reg : iconProviders) {
      final AtlasTexture map = event.getMap();
      if (map != null) {
        reg.registerIcons(map);
      }
    }
  }

  @SuppressWarnings("null") // don't trust modded models to not do stupid things...
  public static @Nonnull TextureAtlasSprite getIconForItem(@Nonnull Item item, int meta) {
    final TextureAtlasSprite icon = Minecraft.getInstance().getItemRenderer().getItemModelMesher().getParticleIcon(item, meta);
    return icon != null ? icon : Minecraft.getInstance().getTextureMapBlocks().getMissingSprite();
  }

}
