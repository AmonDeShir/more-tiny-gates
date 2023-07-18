package pl.amon.moretinygates.gates.multiblock;

import com.dannyandson.tinyredstone.blocks.PanelCellPos;
import com.dannyandson.tinyredstone.blocks.PanelTile;
import com.dannyandson.tinyredstone.blocks.PanelTileRenderer;
import com.dannyandson.tinyredstone.blocks.PosInPanelCell;
import com.dannyandson.tinyredstone.blocks.Side;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;

public abstract class AbstractTinySlaveMultiblock extends AbstractTinyMultiblock {

  protected AbstractTinyMasterMultiblock master = null;
  protected char id = 0;
  protected ResourceLocation texture;
  protected boolean makeDirty;
  
  public abstract ResourceLocation loadUnconnectedTexture(); 
  public abstract String getMultiblockID();

  public AbstractTinySlaveMultiblock() {
    this.texture = loadUnconnectedTexture();
  }

  public void setMaster(AbstractTinyMasterMultiblock master, char id) {
    this.master = master;
    this.id = id;
  }

  public void onRemove(PanelCellPos cellPos) {
    if (this.master != null) {
      this.master.removeSlave(id);
    }
  }

  public boolean tick(PanelCellPos cellPos) {
    if (this.makeDirty) {
      this.makeDirty = false;
      return true;
    }

    return false;
  }

  public int getWeakRsOutput(Side side) {
    if (master == null) {
      return 0;
    }

    return master.getSlaveOutput(side, id);
  }

  public boolean neighborChanged(PanelCellPos cellPos) {
    if (this.master == null) {
      return false;
    }

    this.master.updateSlaveInputs(cellPos, id);

    return false;
  }

  @Override
  public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
    VertexConsumer builder = buffer.getBuffer((alpha == 1.0) ? RenderType.solid() : RenderType.translucent());
    TextureAtlasSprite sprite_panel = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
    TextureAtlasSprite sprite_gate = RenderHelper.getSprite(texture);

    com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack, builder, sprite_gate, sprite_panel, combinedLight, alpha);
  }

  public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
    // overlayBlockInfo.addText("Output", Integer.valueOf(this.output).toString());
    // overlayBlockInfo.setPowerOutput(0);
  }
}
