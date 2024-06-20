package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
import dev.splitwolf.thisorethat.block.RawOreBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThisOreThat.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        MetalBlocks.BLOCKS.getEntries().forEach(this::metalBlockState);

        OreBlocks.BLOCKS.getEntries().forEach(this::oreBlockState);
    }

    private void metalBlockState(RegistryObject<Block> block) {
        blockWithItemAndTexture(block, MetalBlockTexture(block.get()));
    }
    private void oreBlockState(RegistryObject<Block> block) {
        blockWithItemAndTexture(block, OreTexture(block.get()));
    }


    private ResourceLocation MetalBlockTexture(Block block) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/metal/" + name.getPath());
    }

    private ResourceLocation OreTexture(Block block) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/ore/" + name.getPath());
    }

    private void blockWithItemAndTexture(RegistryObject<Block> blockRegistryObject, ResourceLocation texture) {
        simpleBlockWithItem(blockRegistryObject.get(), this.cubeAll(blockRegistryObject.get(), texture));
    }

    public ModelFile cubeAll(Block block, ResourceLocation texture) {
        return models().cubeAll(name(block), texture);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

}
