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
        setMiningLevels();

        OreBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);
        MetalBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);
        RawOreBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);

        MetalBlocks.BLOCKS.getEntries().forEach(this::tagMetalBlocks);
        OreBlocks.BLOCKS.getEntries().forEach(this::tagOres);
        RawOreBlocks.BLOCKS.getEntries().forEach(this::tagRawOreBlocks);
    }

    private void setMiningLevels() {
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                OreBlocks.ALUMINIUM_ORE.get(),
                OreBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                OreBlocks.LEAD_ORE.get(),
                OreBlocks.DEEPSLATE_LEAD_ORE.get(),
                OreBlocks.TIN_ORE.get(),
                OreBlocks.DEEPSLATE_TIN_ORE.get(),
                OreBlocks.ZINC_ORE.get(),
                OreBlocks.DEEPSLATE_ZINC_ORE.get(),
                MetalBlocks.ALUMINIUM_BLOCK.get(),
                MetalBlocks.BRASS_BLOCK.get(),
                MetalBlocks.BRONZE_BLOCK.get(),
                MetalBlocks.LEAD_BLOCK.get(),
                MetalBlocks.SILICON_BLOCK.get(),
                MetalBlocks.NICKEL_BLOCK.get(),
                MetalBlocks.TIN_BLOCK.get(),
                MetalBlocks.ZINC_BLOCK.get(),
                RawOreBlocks.RAW_ALUMINUM_BLOCK.get(),
                RawOreBlocks.RAW_LEAD_BLOCK.get(),
                RawOreBlocks.RAW_TIN_BLOCK.get(),
                RawOreBlocks.RAW_ZINC_BLOCK.get()
        );
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
            OreBlocks.NICKEL_ORE.get(),
            OreBlocks.DEEPSLATE_NICKEL_ORE.get(),
            OreBlocks.SILVER_ORE.get(),
            OreBlocks.DEEPSLATE_SILVER_ORE.get(),
            OreBlocks.PLATINUM_ORE.get(),
            OreBlocks.DEEPSLATE_PLATINUM_ORE.get(),
            OreBlocks.URANIUM_ORE.get(),
            OreBlocks.DEEPSLATE_URANIUM_ORE.get(),
            MetalBlocks.ELECTRUM_BLOCK.get(),
            MetalBlocks.INVAR_BLOCK.get(),
            MetalBlocks.NICKEL_BLOCK.get(),
            MetalBlocks.PLATINUM_BLOCK.get(),
            MetalBlocks.SILVER_BLOCK.get(),
            MetalBlocks.STEEL_BLOCK.get(),
            MetalBlocks.URANIUM_BLOCK.get(),
            RawOreBlocks.RAW_NICKEL_BLOCK.get(),
            RawOreBlocks.RAW_SILVER_BLOCK.get(),
            RawOreBlocks.RAW_PLATINUM_BLOCK.get(),
            RawOreBlocks.RAW_URANIUM_BLOCK.get()
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
