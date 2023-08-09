package pl.amon.moretinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.blocks.PanelCellNeighbor;
import com.dannyandson.tinyredstone.blocks.PanelCellPos;
import com.dannyandson.tinyredstone.blocks.PanelCellSegment;
import com.dannyandson.tinyredstone.blocks.PanelTileRenderer;
import com.dannyandson.tinyredstone.blocks.Side;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import pl.amon.moretinygates.MoreTinyGates;

public class Generator extends AbstractAnalogElement {
  boolean powerOn = true;
  int level = 0;
  
  public static ResourceLocation[] TEXTURE_ON = new ResourceLocation[] { 
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_0"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_1"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_2"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_3"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_4"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_5"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_6"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_7"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_8"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_9"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_10"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_11"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_12"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_13"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_14"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_on_15"),
  };

  public static ResourceLocation[] TEXTURE_OFF = new ResourceLocation[] { 
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_0"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_1"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_2"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_3"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_4"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_5"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_6"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_7"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_8"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_9"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_10"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_11"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_12"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_13"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_14"),
    new ResourceLocation(MoreTinyGates.MODID, "block/generator_off_15"),
  };


  @Override
  public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
    VertexConsumer builder = buffer.getBuffer((alpha == 1.0) ? RenderType.solid() : RenderType.translucent());
    TextureAtlasSprite sprite_panel = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
    TextureAtlasSprite sprite_gate = powerOn ? RenderHelper.getSprite(TEXTURE_ON[level]) : RenderHelper.getSprite(TEXTURE_OFF[level]);

    com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack, builder, sprite_gate, sprite_panel, combinedLight, alpha);
  }

  @Override
  public boolean neighborChanged(PanelCellPos cellPos) {
    PanelCellNeighbor back = cellPos.getNeighbor(Side.BACK);

    boolean powerOn = back == null || back.getWeakRsOutput() == 0;

    if (powerOn != this.powerOn) {
      this.powerOn = powerOn;
      this.output = this.powerOn ? this.level : 0;

      return true;
    }

    return false;
  }

  @Override
  public CompoundTag writeNBT() {
    CompoundTag compoundTag = super.writeNBT();
    compoundTag.putBoolean("powerOn", this.powerOn);
    compoundTag.putInt("level", this.level);

    return compoundTag;
  }

  @Override
  public void readNBT(CompoundTag compoundTag) {
    super.readNBT(compoundTag);

    this.powerOn = compoundTag.getBoolean("powerOn");
    this.level = compoundTag.getInt("level");
  }

  public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
    this.level += 1;
    
    if (this.level >= 16) {
      this.level = 0;
    }

    if (this.powerOn) {
      this.output = this.level;
    }

    return true;
  }

  public boolean hasActivation() {
    return true;
  }
}