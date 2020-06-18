package com.enderio.core.common;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class OreDict {

  public static void registerVanilla() {
    safeRegister("barsIron", Blocks.IRON_BARS);
    safeRegister("blockHopper", Blocks.HOPPER);
    safeRegister("itemCoal", Items.COAL);
    safeRegister("itemCharcoal", new ItemStack(Items.COAL, 1, 1));
    safeRegister("pearlEnderEye", Items.ENDER_EYE);
    safeRegister("itemBlazeRod", Items.BLAZE_ROD);
    safeRegister("itemBlazePowder", Items.BLAZE_POWDER);
    safeRegister("itemClay", Items.CLAY_BALL);
    safeRegister("itemFlint", Items.FLINT);
    safeRegister("itemGhastTear", Items.GHAST_TEAR);
    safeRegister("itemLeather", Items.LEATHER);
    safeRegister("slabWoodOak", new ItemStack(Blocks.OAK_SLAB, 1));
    safeRegister("slabWoodSpruce", new ItemStack(Blocks.SPRUCE_SLAB, 1));
    safeRegister("slabWoodBirch", new ItemStack(Blocks.BIRCH_SLAB, 1));
    safeRegister("slabWoodJungle", new ItemStack(Blocks.JUNGLE_SLAB, 1));
    safeRegister("slabWoodAcacia", new ItemStack(Blocks.ACACIA_SLAB, 1));
    safeRegister("slabWoodDarkOak", new ItemStack(Blocks.DARK_OAK_SLAB, 1));
    safeRegister("slabStone", new ItemStack(Blocks.STONE_SLAB, 1));
    safeRegister("slabSandstone", new ItemStack(Blocks.SANDSTONE_SLAB, 1));
    safeRegister("slabCobblestone", new ItemStack(Blocks.COBBLESTONE_SLAB, 1));
    safeRegister("slabBricks", new ItemStack(Blocks.BRICK_SLAB, 1));
    safeRegister("slabStoneBricks", new ItemStack(Blocks.STONE_BRICK_SLAB, 1));
    safeRegister("slabNetherBrick", new ItemStack(Blocks.NETHER_BRICK_SLAB, 1));
    safeRegister("slabQuartz", new ItemStack(Blocks.QUARTZ_SLAB, 1));
  }

  public static void safeRegister(@Nonnull String name, @Nonnull Block block) {
    safeRegister(name, Item.getItemFromBlock(block));
  }

  public static void safeRegister(@Nonnull String name, @Nonnull Item item) {
    safeRegister(name, new ItemStack(item));
  }

  public static void safeRegister(@Nonnull String name, @Nonnull ItemStack stack) {
    if (!isRegistered(stack, OreDictionary.getOres(name)))
      OreDictionary.registerOre(name, stack);
  }

  private static boolean isRegistered(@Nonnull ItemStack stack, @Nullable List<ItemStack> toCheck) {
    if (toCheck != null) {
      for (ItemStack check : toCheck) {
        if (!stack.isEmpty() && stack.getItem() == check.getItem()
            && (stack.getDamage() == check.getDamage() || stack.getDamage() == OreDictionary.WILDCARD_VALUE)) {
          return true;
        }
      }
    }
    return false;
  }

  private OreDict() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
}