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

    public static final RegistryObject<Item> lead_nugget = registerItem("lead_nugget");
    public static final RegistryObject<Item> silver_nugget = registerItem("silver_nugget");
    public static final RegistryObject<Item> tin_nugget = registerItem("tin_nugget");
    public static final RegistryObject<Item> uranium_nugget = registerItem("uranium_nugget");
    public static final RegistryObject<Item> zinc_nugget = registerItem("zinc_nugget");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerItem(String itemID) {
        return ITEMS.register(itemID,
                () -> new Item(new Item.Properties())
        );
    }
}
