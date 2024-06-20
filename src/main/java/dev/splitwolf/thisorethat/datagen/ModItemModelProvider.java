package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.block.RawOreBlocks;
import dev.splitwolf.thisorethat.item.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThisOreThat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerMetalItems();
    }

    private void registerMetalItems() {
        IngotItems.ITEMS.getEntries().forEach(this::ingotItem);
        RawOreItems.ITEMS.getEntries().forEach(this::rawOreItem);
        MetalBlocks.BLOCK_ITEMS.getEntries().forEach(this::blockItem);
        OreBlocks.BLOCK_ITEMS.getEntries().forEach(this::blockItem);
        RawOreBlocks.BLOCK_ITEMS.getEntries().forEach(this::blockItem);
        NuggetItems.ITEMS.getEntries().forEach(this::nuggetItem);
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),new ResourceLocation(ThisOreThat.MOD_ID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder ingotItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                new ResourceLocation(ThisOreThat.MOD_ID, "item/ingot/" + item.getId().getPath()));
    }

    private ItemModelBuilder rawOreItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                        new ResourceLocation(ThisOreThat.MOD_ID, "item/raw_ore/" + item.getId().getPath()));
    }

    private ItemModelBuilder nuggetItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                        new ResourceLocation(ThisOreThat.MOD_ID, "item/nugget/" + item.getId().getPath()));
    }


}
