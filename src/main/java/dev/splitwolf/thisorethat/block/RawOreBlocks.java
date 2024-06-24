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

@SuppressWarnings("unused")
public class RawOreBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThisOreThat.MOD_ID);

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = addRawOreBlock("raw_aluminum_block");
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = addRawOreBlock("raw_lead_block");
    public static final RegistryObject<Block> RAW_NICKEL_BLOCK = addRawOreBlock("raw_nickel_block");
    public static final RegistryObject<Block> RAW_PLATINUM_BLOCK = addRawOreBlock("raw_platinum_block");
    public static final RegistryObject<Block> RAW_SALT_BLOCK = addRawOreBlock("raw_salt_block");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = addRawOreBlock("raw_silver_block");
    public static final RegistryObject<Block> RAW_SULFUR_BLOCK = addRawOreBlock("raw_sulfur_block");
    public static final RegistryObject<Block> RAW_TIN_BLOCK = addRawOreBlock("raw_tin_block");
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = addRawOreBlock("raw_uranium_block");
    public static final RegistryObject<Block> RAW_ZINC_BLOCK = addRawOreBlock("raw_zinc_block");

    private static RegistryObject<Block> addRawOreBlock(String name) {
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
