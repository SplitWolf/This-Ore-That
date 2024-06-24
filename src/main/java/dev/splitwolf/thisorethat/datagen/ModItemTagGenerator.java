package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.item.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, ThisOreThat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        IngotItems.ITEMS.getEntries().forEach(this::tagIngots);
        RawOreItems.ITEMS.getEntries().forEach(this::tagRawMaterials);
        NuggetItems.ITEMS.getEntries().forEach(this::tagNuggets);
        DustItems.ITEMS.getEntries().forEach(this::tagDustItems);
        GearItems.ITEMS.getEntries().forEach(this::tagGearItems);
        SheetItems.ITEMS.getEntries().forEach(this::tagSheetItems);

        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "silicon"));
        this.tag(tag).add(IngotItems.SILICON_INGOT.get());
    }

    private void tagIngots(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "ingots/" + item.getId().getPath().replace("_ingot","")));
        this.tag(tag).add(item.get());
    }

    private void tagNuggets(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "nuggets/" + item.getId().getPath().replace("_nugget","")));
        this.tag(tag).add(item.get());
    }


    private void tagRawMaterials(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "raw_materials/" + item.getId().getPath().replace("raw_","")));
        this.tag(tag).add(item.get());
    }

    private void tagDustItems(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "dusts/" + item.getId().getPath().replace("raw_","")));
        this.tag(tag).add(item.get());
    }

    private void tagGearItems(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "gear/" + item.getId().getPath().replace("raw_","")));
        this.tag(tag).add(item.get());
    }

    private void tagSheetItems(RegistryObject<Item> item) {
        TagKey<Item> tag = ItemTags.create(new ResourceLocation("forge",
                "plates/" + item.getId().getPath().replace("raw_","")));
        this.tag(tag).add(item.get());
    }

}
