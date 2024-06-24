package dev.splitwolf.thisorethat.block;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MetalBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThisOreThat.MOD_ID);

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

    public static final RegistryObject<Block> ALUMINIUM_BLOCK = addMetalBlock("aluminum_block");
    public static final RegistryObject<Block> BRASS_BLOCK = addMetalBlock("brass_block");
    public static final RegistryObject<Block> BRONZE_BLOCK = addMetalBlock("bronze_block");
    public static final RegistryObject<Block> ELECTRUM_BLOCK = addMetalBlock("electrum_block");
    public static final RegistryObject<Block> INVAR_BLOCK = addMetalBlock("invar_block");
    public static final RegistryObject<Block> LEAD_BLOCK = addMetalBlock("lead_block");
    public static final RegistryObject<Block> NICKEL_BLOCK = addMetalBlock("nickel_block");
    public static final RegistryObject<Block> PLATINUM_BLOCK = addMetalBlock("platinum_block");
    public static final RegistryObject<Block> SALT_BLOCK = addMetalBlock("salt_block");
    public static final RegistryObject<Block> SILICON_BLOCK = addMetalBlock("silicon_block");
    public static final RegistryObject<Block> SILVER_BLOCK = addMetalBlock("silver_block");
    public static final RegistryObject<Block> STEEL_BLOCK = addMetalBlock("steel_block");
    public static final RegistryObject<Block> SULFUR_BLOCK = addMetalBlock("sulfur_block");
    public static final RegistryObject<Block> TIN_BLOCK = addMetalBlock("tin_block");
    public static final RegistryObject<Block> URANIUM_BLOCK = addMetalBlock("uranium_block");
    public static final RegistryObject<Block> ZINC_BLOCK = addMetalBlock("zinc_block");



    private static RegistryObject<Block> addMetalBlock(String name) {
        return registerBlock(name,() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        BLOCK_ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
