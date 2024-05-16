package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.item.MetalBlocks;
import dev.splitwolf.thisorethat.item.IngotItems;
import dev.splitwolf.thisorethat.item.OreBlocks;
import dev.splitwolf.thisorethat.item.RawOreItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        registerMetalBlockRecipes(pWriter);
        registerSmeltingRecipes(pWriter);
    }

    private void registerMetalBlockRecipes(Consumer<FinishedRecipe> pWriter) {
        MetalBlocks.BLOCKS.getEntries().forEach(block -> {
            String materialType = block.getId().getPath().replace("_block","");
            RegistryObject<Item> unpackedItem = IngotItems.ITEMS.getEntries().stream().filter(item -> item.getId().getPath().contains(materialType)).findFirst().get();
            ninePackingUnpackingRecipes(RecipeCategory.BUILDING_BLOCKS, block.get(), RecipeCategory.BUILDING_BLOCKS,unpackedItem.get(), pWriter);
        });
    }

    private void registerSmeltingRecipes(Consumer<FinishedRecipe> pWriter) {
        //TODO: blasting
        OreBlocks.BLOCK_ITEMS.getEntries().forEach(item -> {
            //TODO: Remove if
            if(item.getId().getPath().matches(".*(nickel|tin_).*")) {
                return;
            }
            String materialType = item.getId().getPath().replace("_ore","").replace("deepslate_","");
            RegistryObject<Item> ingotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().contains(materialType+"_")).findFirst().get();

            //TODO: Make recipes disablable.
            //TODO: Update Time and Exp
            oreCook(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(item.get()), RecipeCategory.BUILDING_BLOCKS,ingotItem.get(), 0.35f,200, "smelting");
        });

        RawOreItems.ITEMS.getEntries().forEach(item -> {
            //TODO: Remove if
            if(item.getId().getPath().matches(".*(nickel|tin_).*")) {
                return;
            }
            String materialType = item.getId().getPath().replace("raw_","");
            RegistryObject<Item> ingotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().contains(materialType)).findFirst().get();
            oreCook(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(item.get()), RecipeCategory.BUILDING_BLOCKS,ingotItem.get(), 0.35f,200, "smelting");
        });

    }


    private void oreCook(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike),
                    pCategory,
                    pResult,
                    pExperience,
                    pCookingTime,
                    pCookingSerializer
            )
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(ThisOreThat.MOD_ID, pRecipeName + "/" + getItemName(pResult) + "_from_" + getItemName(itemlike)));
        }
    }


    private void ninePackingUnpackingRecipes(RecipeCategory packedCategory, ItemLike packedItem, RecipeCategory unpackedCategory, ItemLike unpackedItem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(packedCategory, packedItem)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', unpackedItem)
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(consumer, new ResourceLocation(ThisOreThat.MOD_ID, "from/ingot/" + getItemName(packedItem)));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packedItem)
                .unlockedBy(getHasName(packedItem),has(packedItem))
                .save(consumer,new ResourceLocation(ThisOreThat.MOD_ID, "from/block/" + getItemName(unpackedItem)));
    }
}
