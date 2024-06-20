package dev.splitwolf.thisorethat;

import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
import dev.splitwolf.thisorethat.block.RawOreBlocks;
import dev.splitwolf.thisorethat.item.IngotItems;
import dev.splitwolf.thisorethat.item.NuggetItems;
import dev.splitwolf.thisorethat.item.RawOreItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThisOreThat.MOD_ID);


    public static final RegistryObject<CreativeModeTab> THIS_ORE_THAT_TAB = CREATIVE_MODE_TABS.register("this_ore_that_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(IngotItems.BRASS_INGOT.get()))
                    .title(Component.translatable("creative_tab.this_ore_that_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        IngotItems.ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                        MetalBlocks.BLOCK_ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                        NuggetItems.ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                        OreBlocks.BLOCK_ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                        RawOreItems.ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
