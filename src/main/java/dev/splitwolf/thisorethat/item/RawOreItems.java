package dev.splitwolf.thisorethat.item;

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

public class RawOreItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

    //ELEMENT
    public static final RegistryObject<Item> RAW_ALUMINUM = registerItem("raw_aluminum");
    public static final RegistryObject<Item> RAW_LEAD = registerItem("raw_lead");
    public static final RegistryObject<Item> RAW_NICKEL = registerItem("raw_nickel");
    public static final RegistryObject<Item> RAW_PLATINUM = registerItem("raw_platinum");
    public static final RegistryObject<Item> RAW_SALT = registerItem("raw_salt");
    public static final RegistryObject<Item> RAW_SILVER = registerItem("raw_silver");
    public static final RegistryObject<Item> RAW_SULFUR = registerItem("raw_sulfur");
    public static final RegistryObject<Item> RAW_TIN = registerItem("raw_tin");
    public static final RegistryObject<Item> RAW_URANIUM = registerItem("raw_uranium");
    public static final RegistryObject<Item> RAW_ZINC = registerItem("raw_zinc");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
