package dev.splitwolf.thisorethat.item;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GearItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);


public static final RegistryObject<Item> ALUMINUM_GEAR = registerItem("aluminum_gear");
public static final RegistryObject<Item> BRASS_GEAR = registerItem("brass_gear");
public static final RegistryObject<Item> BRONZE_GEAR = registerItem("bronze_gear");
public static final RegistryObject<Item> COPPER_GEAR = registerItem("copper_gear");
public static final RegistryObject<Item> DIAMOND_GEAR = registerItem("diamond_gear");
public static final RegistryObject<Item> ELECTRUM_GEAR = registerItem("electrum_gear");
public static final RegistryObject<Item> EMERALD_GEAR = registerItem("emerald_gear");
public static final RegistryObject<Item> INVAR_GEAR = registerItem("invar_gear");
public static final RegistryObject<Item> LEAD_GEAR = registerItem("lead_gear");
public static final RegistryObject<Item> NETHERITE_GEAR = registerItem("netherite_gear");
public static final RegistryObject<Item> NICKEL_GEAR = registerItem("nickel_gear");
public static final RegistryObject<Item> PLATINUM_GEAR = registerItem("platinum_gear");
public static final RegistryObject<Item> SILICON_GEAR = registerItem("silicon_gear");
public static final RegistryObject<Item> SILVER_GEAR = registerItem("silver_gear");
public static final RegistryObject<Item> STEEL_GEAR = registerItem("steel_gear");
public static final RegistryObject<Item> TIN_GEAR = registerItem("tin_gear");
public static final RegistryObject<Item> URANIUM_GEAR = registerItem("uranium_gear");
public static final RegistryObject<Item> ZINC_GEAR = registerItem("zinc_gear");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
