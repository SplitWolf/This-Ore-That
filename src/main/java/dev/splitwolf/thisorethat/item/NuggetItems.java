package dev.splitwolf.thisorethat.item;

import dev.splitwolf.thisorethat.ThisOreThat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NuggetItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThisOreThat.MOD_ID);


    public static final RegistryObject<Item> ALUMINUM_NUGGET = registerItem("aluminum_nugget");
    public static final RegistryObject<Item> BRASS_NUGGET = registerItem("brass_nugget");
    public static final RegistryObject<Item> BRONZE_NUGGET = registerItem("bronze_nugget");
    public static final RegistryObject<Item> COPPER_NUGGET = registerItem("copper_nugget");
    public static final RegistryObject<Item> DIAMOND_NUGGET = registerItem("diamond_nugget");
    public static final RegistryObject<Item> ELECTRUM_NUGGET = registerItem("electrum_nugget");
    public static final RegistryObject<Item> EMERALD_NUGGET = registerItem("emerald_nugget");
    public static final RegistryObject<Item> INVAR_NUGGET = registerItem("invar_nugget");
    public static final RegistryObject<Item> LAPIS_LAZULI_NUGGET = registerItem("lapis_lazuli_nugget");
    public static final RegistryObject<Item> LEAD_NUGGET = registerItem("lead_nugget");
    public static final RegistryObject<Item> NETHERITE_NUGGET = registerItem("netherite_nugget");
    public static final RegistryObject<Item> NICKEL_NUGGET = registerItem("nickel_nugget");
    public static final RegistryObject<Item> PLATINUM_NUGGET = registerItem("platinum_nugget");
    public static final RegistryObject<Item> QUARTZ_NUGGET = registerItem("quartz_nugget");
    public static final RegistryObject<Item> SILICON_NUGGET = registerItem("silicon_nugget");
    public static final RegistryObject<Item> SILVER_NUGGET = registerItem("silver_nugget");
    public static final RegistryObject<Item> STEEL_NUGGET = registerItem("steel_nugget");
    public static final RegistryObject<Item> TIN_NUGGET = registerItem("tin_nugget");
    public static final RegistryObject<Item> URANIUM_NUGGET = registerItem("uranium_nugget");
    public static final RegistryObject<Item> ZINC_NUGGET = registerItem("zinc_nugget");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
