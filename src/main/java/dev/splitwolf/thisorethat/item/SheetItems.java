package dev.splitwolf.thisorethat.item;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class SheetItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);


public static final RegistryObject<Item> ALUMINUM_SHEET = registerItem("aluminum_sheet");
public static final RegistryObject<Item> BRASS_SHEET = registerItem("brass_sheet");
public static final RegistryObject<Item> BRONZE_SHEET = registerItem("bronze_sheet");
public static final RegistryObject<Item> COPPER_SHEET = registerItem("copper_sheet");
public static final RegistryObject<Item> DIAMOND_SHEET = registerItem("diamond_sheet");
public static final RegistryObject<Item> ELECTRUM_SHEET = registerItem("electrum_sheet");
public static final RegistryObject<Item> EMERALD_SHEET = registerItem("emerald_sheet");
public static final RegistryObject<Item> INVAR_SHEET = registerItem("invar_sheet");
public static final RegistryObject<Item> LAPIS_LAZULI_SHEET = registerItem("lapis_lazuli_sheet");
public static final RegistryObject<Item> LEAD_SHEET = registerItem("lead_sheet");
public static final RegistryObject<Item> NETHERITE_SHEET = registerItem("netherite_sheet");
public static final RegistryObject<Item> NICKEL_SHEET = registerItem("nickel_sheet");
public static final RegistryObject<Item> PLATINUM_SHEET = registerItem("platinum_sheet");
public static final RegistryObject<Item> QUARTZ_SHEET = registerItem("quartz_sheet");
public static final RegistryObject<Item> SILICON_SHEET = registerItem("silicon_sheet");
public static final RegistryObject<Item> SILVER_SHEET = registerItem("silver_sheet");
public static final RegistryObject<Item> STEEL_SHEET = registerItem("steel_sheet");
public static final RegistryObject<Item> TIN_SHEET = registerItem("tin_sheet");
public static final RegistryObject<Item> URANIUM_SHEET = registerItem("uranium_sheet");
public static final RegistryObject<Item> ZINC_SHEET = registerItem("zinc_sheet");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
