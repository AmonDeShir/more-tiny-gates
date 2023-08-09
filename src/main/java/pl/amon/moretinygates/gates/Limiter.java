package pl.amon.moretinygates.gates;

import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import pl.amon.moretinygates.MoreTinyGates;

public class Limiter extends AbstractAnalogElement {
  public static ResourceLocation TEXTURE_ON = new ResourceLocation(MoreTinyGates.MODID, "block/limiter_on");
  public static ResourceLocation TEXTURE_OFF = new ResourceLocation(MoreTinyGates.MODID, "block/limiter_off");

  @Override
  public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
    VertexConsumer builder = buffer.getBuffer((alpha == 1.0) ? RenderType.solid() : RenderType.translucent());
    TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
    TextureAtlasSprite gate = output > 0 ? RenderHelper.getSprite(TEXTURE_ON): RenderHelper.getSprite(TEXTURE_OFF);

    com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack, builder, gate, sprite, combinedLight, alpha);
  }

  @Override
  public boolean neighborChanged(PanelCellPos cellPos) {
    PanelCellNeighbor back = cellPos.getNeighbor(Side.BACK);
    PanelCellNeighbor right = cellPos.getNeighbor(Side.RIGHT);

    int min = right != null ? right.getWeakRsOutput() : 0;
    int output = back != null ? Math.min(min, back.getWeakRsOutput()) : 0;

    if (output != this.output) {
      this.output = output;
      return true;
    }

    return false;
  }
}