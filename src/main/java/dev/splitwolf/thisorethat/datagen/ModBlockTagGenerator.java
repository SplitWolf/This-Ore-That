package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
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
        OreBlocks.BLOCKS.getEntries().forEach(this::needsIronTool);
        MetalBlocks.BLOCKS.getEntries().forEach(this::needsIronTool);

        //TODO: Are all blocks mineable with pick?
        OreBlocks.BLOCKS.getEntries().forEach(this::addMineableWithPick);
        this.addMineableWithPick(MetalBlocks.PLATINUM_BLOCK);

        MetalBlocks.BLOCKS.getEntries().forEach(this::tagMetalBlocks);
        OreBlocks.BLOCKS.getEntries().forEach(this::tagOres);
    }

    private void tagMetalBlocks(RegistryObject<Block> block) {
        TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge",
                "blocks/" + block.getId().getPath().replace("_block","")));
        this.tag(tag).add(block.get());
    }
    private void tagOres(RegistryObject<Block> block) {
        TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge",
                "ores/" + block.getId().getPath().replace("_ore","").replace("deepslate_","")));
        this.tag(tag).add(block.get());
//  TODO: This this tag added automatically?
//        tag = BlockTags.create(new ResourceLocation("forge", "ores"));
//        this.tag(tag).add(block.get());

        tag = BlockTags.create(new ResourceLocation("forge", "ores_in_ground/stone"));
        if(!block.getId().getPath().contains("deepslate"))
            this.tag(tag).add(block.get());

        tag = BlockTags.create(new ResourceLocation("forge", "ores_in_ground/deepslate"));
        if(block.getId().getPath().contains("deepslate"))
            this.tag(tag).add(block.get());
    }

    private void needsIronTool(RegistryObject<Block> block) {
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(block.get());
    }

    private void addMineableWithPick(RegistryObject<Block> block) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
    }
}
