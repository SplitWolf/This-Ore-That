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

public class OreBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThisOreThat.MOD_ID);

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

    public static final RegistryObject<Block> ALUMINIUM_ORE = addOre("aluminum_ore");
    public static final RegistryObject<Block> DEEPSLATE_ALUMINIUM_ORE = addOre("deepslate_aluminum_ore");

    public static final RegistryObject<Block> LEAD_ORE = addOre("lead_ore");
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = addOre("deepslate_lead_ore");


    public static final RegistryObject<Block> NICKEL_ORE = addOre("nickel_ore");
    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = addOre("deepslate_nickel_ore");

    public static final RegistryObject<Block> PLATINUM_ORE = addOre("platinum_ore");
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = addOre("deepslate_platinum_ore");

    public static final RegistryObject<Block> SALT_ORE = addOre("salt_ore");
    public static final RegistryObject<Block> DEEPSLATE_SALT_ORE = addOre("deepslate_salt_ore");

    public static final RegistryObject<Block> SILVER_ORE = addOre("silver_ore");
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = addOre("deepslate_silver_ore");

    public static final RegistryObject<Block> SULFUR_ORE = addOre("sulfur_ore");
    public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE = addOre("deepslate_sulfur_ore");

    public static final RegistryObject<Block> TIN_ORE = addOre("tin_ore");
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = addOre("deepslate_tin_ore");

    public static final RegistryObject<Block> URANIUM_ORE = addOre("uranium_ore");
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = addOre("deepslate_uranium_ore");

    public static final RegistryObject<Block> ZINC_ORE = addOre("zinc_ore");
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = addOre("deepslate_zinc_ore");

    private static RegistryObject<Block> addOre(String name) {
        if(name.contains("deepslate")) {
            return registerBlock(name,() -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
        }
        return registerBlock(name,() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
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
