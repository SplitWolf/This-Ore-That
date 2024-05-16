package dev.splitwolf.thisorethat.datagen.loot;

import dev.splitwolf.thisorethat.item.MetalBlocks;
import dev.splitwolf.thisorethat.item.OreBlocks;
import dev.splitwolf.thisorethat.item.RawOreItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        MetalBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(this::dropSelf);


        oreDrop(OreBlocks.LEAD_ORE,OreBlocks.DEEPSLATE_LEAD_ORE,RawOreItems.RAW_LEAD);
        oreDrop(OreBlocks.NICKEL_ORE,OreBlocks.DEEPSLATE_NICKEL_ORE,RawOreItems.RAW_NICKEL);
        oreDrop(OreBlocks.PLATINUM_ORE,OreBlocks.DEEPSLATE_PLATINUM_ORE,RawOreItems.RAW_PLATINUM);
        oreDrop(OreBlocks.TIN_ORE,OreBlocks.DEEPSLATE_TIN_ORE,RawOreItems.RAW_TIN);
        oreDrop(OreBlocks.ZINC_ORE,OreBlocks.DEEPSLATE_ZINC_ORE,RawOreItems.RAW_ZINC);
    }

    private void oreDrop(RegistryObject<Block> ore, @Nullable RegistryObject<Block> deepslate_ore, RegistryObject<Item> drop) {
        this.add(ore.get(),
                block -> this.createOreDrop(block, drop.get())
        );
        if(deepslate_ore != null) {
            this.add(deepslate_ore.get(),
                    block -> this.createOreDrop(block, drop.get())
            );
        }
    }

//    @Override
//    protected LootTable.Builder createOreDrop(Block pBlock, Item pItem) {
//        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
//    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        Collection<RegistryObject<Block>> blockList = new ArrayList<>();
        blockList.addAll(MetalBlocks.BLOCKS.getEntries());
        blockList.addAll(OreBlocks.BLOCKS.getEntries());
        return blockList.stream().map(RegistryObject::get)::iterator;
    }
}
