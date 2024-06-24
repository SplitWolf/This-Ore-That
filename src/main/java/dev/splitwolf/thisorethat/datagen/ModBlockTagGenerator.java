package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
import dev.splitwolf.thisorethat.block.RawOreBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ThisOreThat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        //TODO: Fix mining level
        setMiningLevels();

        //TODO: Are all blocks mineable with pick?
        OreBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);
        MetalBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);
        MetalBlocks.BLOCKS.getEntries().forEach(this::tagMetalBlocks);
        OreBlocks.BLOCKS.getEntries().forEach(this::tagOres);
        RawOreBlocks.BLOCKS.getEntries().forEach(this::tagRawOreBlocks);
    }

    private void setMiningLevels() {
        TagKey<Block> wood_tag = BlockTags.create(new ResourceLocation("forge",
                "needs_wood_tool"));
        //TODO: Check tags
        this.tag(wood_tag).add(
            OreBlocks.SALT_ORE.get(),
            OreBlocks.DEEPSLATE_SALT_ORE.get(),
            OreBlocks.SULFUR_ORE.get(),
            OreBlocks.DEEPSLATE_SULFUR_ORE.get()
        );
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                OreBlocks.TIN_ORE.get(),
                OreBlocks.DEEPSLATE_TIN_ORE.get(),
                OreBlocks.ALUMINIUM_ORE.get(),
                OreBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                OreBlocks.ZINC_ORE.get(),
                OreBlocks.DEEPSLATE_ZINC_ORE.get(),
                OreBlocks.NICKEL_ORE.get(),
                OreBlocks.DEEPSLATE_NICKEL_ORE.get(),
                MetalBlocks.TIN_BLOCK.get(),
                MetalBlocks.ZINC_BLOCK.get(),
                MetalBlocks.SILICON_BLOCK.get()
        );
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
            OreBlocks.SILVER_ORE.get(),
            OreBlocks.DEEPSLATE_SILVER_ORE.get(),
            OreBlocks.LEAD_ORE.get(),
            OreBlocks.DEEPSLATE_LEAD_ORE.get(),
            OreBlocks.PLATINUM_ORE.get(),
            OreBlocks.DEEPSLATE_PLATINUM_ORE.get(),
            OreBlocks.URANIUM_ORE.get(),
            OreBlocks.DEEPSLATE_URANIUM_ORE.get(),
            MetalBlocks.SILVER_BLOCK.get(),
            MetalBlocks.LEAD_BLOCK.get(),
            MetalBlocks.PLATINUM_BLOCK.get(),
            MetalBlocks.URANIUM_BLOCK.get()
        );
    }

    private void tagRawOreBlocks(RegistryObject<Block> block) {
        TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge",
                "blocks/" + block.getId().getPath().replace("_block","")));
        this.tag(tag).add(block.get());
        TagKey<Block> storage = BlockTags.create(new ResourceLocation("forge",
                "storage_blocks/" + block.getId().getPath().replace("_block","")));
        this.tag(storage).add(block.get());
    }

    private void tagMetalBlocks(RegistryObject<Block> block) {
        TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge",
                "blocks/" + block.getId().getPath().replace("_block","")));
        this.tag(tag).add(block.get());
        TagKey<Block> storage = BlockTags.create(new ResourceLocation("forge",
                "storage_blocks/" + block.getId().getPath().replace("_block","")));
        this.tag(storage).add(block.get());
    }
    private void tagOres(RegistryObject<Block> block) {
        TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge",
                "ores/" + block.getId().getPath().replace("_ore","").replace("deepslate_","")));
        this.tag(tag).add(block.get());

        tag = BlockTags.create(new ResourceLocation("forge", "ores"));
        this.tag(tag).add(block.get());

        tag = BlockTags.create(new ResourceLocation("forge", "ores_in_ground/stone"));
        if(!block.getId().getPath().contains("deepslate"))
            this.tag(tag).add(block.get());

        tag = BlockTags.create(new ResourceLocation("forge", "ores_in_ground/deepslate"));
        if(block.getId().getPath().contains("deepslate"))
            this.tag(tag).add(block.get());
    }

    private void addMineableWithPick(RegistryObject<Block> block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }

}
