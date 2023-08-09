package pl.amon.moretinygates.gates;

import com.dannyandson.tinygates.gates.AbstractGate;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import pl.amon.moretinygates.MoreTinyGates;

public class NORGate extends AbstractGate {
  public static ResourceLocation TEXTURE_NOR_GATE_ON = new ResourceLocation(MoreTinyGates.MODID, "block/nor_gate_on");
  public static ResourceLocation TEXTURE_NOR_GATE_OFF = new ResourceLocation(MoreTinyGates.MODID, "block/nor_gate_off");

  @Override
  public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
    VertexConsumer builder = buffer.getBuffer((alpha == 1.0) ? RenderType.solid() : RenderType.translucent());
    TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
    TextureAtlasSprite sprite_nand_gate = output ? RenderHelper.getSprite(TEXTURE_NOR_GATE_ON): RenderHelper.getSprite(TEXTURE_NOR_GATE_OFF);

    com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack, builder, sprite_nand_gate, sprite, combinedLight, alpha);
  }

  @Override
  public boolean neighborChanged(PanelCellPos cellPos) {
    PanelCellNeighbor right = cellPos.getNeighbor(Side.RIGHT), left = cellPos.getNeighbor(Side.LEFT);

    boolean output = (right == null && right.getWeakRsOutput() <= 0) && (left == null && left.getWeakRsOutput() <= 0);

    if (output != this.output) {
      this.output = output;
      return true;
    }

    return false;
  }
}