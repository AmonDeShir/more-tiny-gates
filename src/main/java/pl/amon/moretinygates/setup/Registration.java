package pl.amon.moretinygates.setup;

import com.dannyandson.tinygates.items.GateBlockItem;
import com.dannyandson.tinygates.items.PanelCellGateItem;
import com.dannyandson.tinyredstone.TinyRedstone;
import com.dannyandson.tinyredstone.network.PanelCellSync;

import pl.amon.moretinygates.gates.Diode;
import pl.amon.moretinygates.gates.Generator;
import pl.amon.moretinygates.gates.Limiter;
import pl.amon.moretinygates.gates.NANDGate;
import pl.amon.moretinygates.gates.NORGate;
import pl.amon.moretinygates.gates.XNORGate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.amon.moretinygates.MoreTinyGates;
import pl.amon.moretinygates.blocks.DiodeBlock;
import pl.amon.moretinygates.blocks.GeneratorBlock;
import pl.amon.moretinygates.blocks.GeneratorBlockEntity;
import pl.amon.moretinygates.blocks.LimiterBlock;
import pl.amon.moretinygates.blocks.NANDGateBlock;
import pl.amon.moretinygates.blocks.NORGateBlock;
import pl.amon.moretinygates.blocks.XNORGateBlock;

public class Registration {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreTinyGates.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreTinyGates.MODID);
  private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoreTinyGates.MODID);
  private static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreTinyGates.MODID);

  public static final NANDGateBlock NAND_GATE_BLOCK = new NANDGateBlock(ITEMS, BLOCKS, BLOCK_ENTITIES);
  public static final NORGateBlock NOR_GATE_BLOCK = new NORGateBlock(ITEMS, BLOCKS, BLOCK_ENTITIES);
  public static final XNORGateBlock XNOR_GATE_BLOCK = new XNORGateBlock(ITEMS, BLOCKS, BLOCK_ENTITIES);
  public static final DiodeBlock DIODE_BLOCK = new DiodeBlock(ITEMS, BLOCKS, BLOCK_ENTITIES);
  public static final LimiterBlock LIMITER_BLOCK = new LimiterBlock(ITEMS, BLOCKS, BLOCK_ENTITIES);

  public static final RegistryObject<Block> GENERATOR_BLOCK = BLOCKS.register("generator_block", GeneratorBlock::new);
  public static final RegistryObject<Item> GENERATOR_ITEM = ITEMS.register("generator_item", () -> new GateBlockItem(GENERATOR_BLOCK.get()));
  public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("generator_block", () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, GENERATOR_BLOCK.get()).build(null));
  
  public static final RegistryObject<Item> TINY_NAND_GATE_ITEM = ITEMS.register("tiny_nand_gate", PanelCellGateItem::new);
  public static final RegistryObject<Item> TINY_NOR_GATE_ITEM = ITEMS.register("tiny_nor_gate", PanelCellGateItem::new);
  public static final RegistryObject<Item> TINY_XNOR_GATE_ITEM = ITEMS.register("tiny_xnor_gate", PanelCellGateItem::new);
  public static final RegistryObject<Item> TINY_DIODE_ITEM = ITEMS.register("tiny_diode", PanelCellGateItem::new);
  public static final RegistryObject<Item> TINY_LIMITER_ITEM = ITEMS.register("tiny_limiter", PanelCellGateItem::new);
  public static final RegistryObject<Item> TINY_GENERATOR_ITEM = ITEMS.register("tiny_generator", PanelCellGateItem::new);

  public static RegistryObject<CreativeModeTab> CREATIVE_TAB = TAB.register("moretinygatestab", () ->
    CreativeModeTab.builder()
      .title(Component.translatable("moretinygates"))
      .icon(() -> new ItemStack(Registration.NOR_GATE_BLOCK.item.get()))
      .displayItems((parameters,output) ->ITEMS.getEntries().forEach(o -> output.accept(o.get())))
      .build());

  public static void registerPanelCells(){
    TinyRedstone.registerPanelCell(NANDGate.class, TINY_NAND_GATE_ITEM.get());
    TinyRedstone.registerPanelCell(NORGate.class, TINY_NOR_GATE_ITEM.get());
    TinyRedstone.registerPanelCell(XNORGate.class, TINY_XNOR_GATE_ITEM.get());
    TinyRedstone.registerPanelCell(Diode.class, TINY_DIODE_ITEM.get());
    TinyRedstone.registerPanelCell(Limiter.class, TINY_LIMITER_ITEM.get());
    TinyRedstone.registerPanelCell(Generator.class, TINY_GENERATOR_ITEM.get());
  }

  public static void registerTinyRedstoneNetworkHandlers(SimpleChannel INSTANCE, int id){
    INSTANCE.messageBuilder(PanelCellSync.class,id)
      .encoder(PanelCellSync::toBytes)
      .decoder(PanelCellSync::new)
      .consumerNetworkThread(PanelCellSync::handle)
      .add();
  }

  public static void register() {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    TAB.register(FMLJavaModLoadingContext.get().getModEventBus());
  }
}
