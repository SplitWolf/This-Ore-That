package dev.splitwolf.thisorethat.item;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class IngotItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

    public static final RegistryObject<Item> ALUMINUM_INGOT = registerItem("aluminum_ingot");
    public static final RegistryObject<Item> BRASS_INGOT = registerItem("brass_ingot");
    public static final RegistryObject<Item> BRONZE_INGOT = registerItem("bronze_ingot");
    public static final RegistryObject<Item> ELECTRUM_INGOT = registerItem("electrum_ingot");
    public static final RegistryObject<Item> INVAR_INGOT = registerItem("invar_ingot");
    public static final RegistryObject<Item> LEAD_INGOT = registerItem("lead_ingot");
    public static final RegistryObject<Item> NICKEL_INGOT = registerItem("nickel_ingot");
    public static final RegistryObject<Item> PLATINUM_INGOT = registerItem("platinum_ingot");
    public static final RegistryObject<Item> SILICON_INGOT = registerItem("silicon_ingot");
    public static final RegistryObject<Item> SILVER_INGOT = registerItem("silver_ingot");
    public static final RegistryObject<Item> STEEL_INGOT = registerItem("steel_ingot");
    public static final RegistryObject<Item> TIN_INGOT = registerItem("tin_ingot");
    public static final RegistryObject<Item> URANIUM_INGOT = registerItem("uranium_ingot");
    public static final RegistryObject<Item> ZINC_INGOT = registerItem("zinc_ingot");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
