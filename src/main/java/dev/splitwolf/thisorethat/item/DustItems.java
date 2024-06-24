package dev.splitwolf.thisorethat.item;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class DustItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);

public static final RegistryObject<Item> ALUMINUM_DUST = registerItem("aluminum_dust");
public static final RegistryObject<Item> BRASS_DUST = registerItem("brass_dust");
public static final RegistryObject<Item> BRONZE_DUST = registerItem("bronze_dust");
public static final RegistryObject<Item> COPPER_DUST = registerItem("copper_dust");
public static final RegistryObject<Item> DIAMOND_DUST = registerItem("diamond_dust");
public static final RegistryObject<Item> ELECTRUM_DUST = registerItem("electrum_dust");
public static final RegistryObject<Item> EMERALD_DUST = registerItem("emerald_dust");
public static final RegistryObject<Item> INVAR_DUST = registerItem("invar_dust");
public static final RegistryObject<Item> LAPIS_LAZULI_DUST = registerItem("lapis_lazuli_dust");
public static final RegistryObject<Item> LEAD_DUST = registerItem("lead_dust");
public static final RegistryObject<Item> NETHERITE_DUST = registerItem("netherite_dust");
public static final RegistryObject<Item> NICKEL_DUST = registerItem("nickel_dust");
public static final RegistryObject<Item> PLATINUM_DUST = registerItem("platinum_dust");
public static final RegistryObject<Item> QUARTZ_DUST = registerItem("quartz_dust");
public static final RegistryObject<Item> SILICON_DUST = registerItem("silicon_dust");
public static final RegistryObject<Item> SILVER_DUST = registerItem("silver_dust");
public static final RegistryObject<Item> STEEL_DUST = registerItem("steel_dust");
public static final RegistryObject<Item> TIN_DUST = registerItem("tin_dust");
public static final RegistryObject<Item> URANIUM_DUST = registerItem("uranium_dust");
public static final RegistryObject<Item> ZINC_DUST = registerItem("zinc_dust");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
