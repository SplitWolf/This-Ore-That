package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.ThisOreThat;
import dev.splitwolf.thisorethat.item.*;
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
        registerNuggetRecipes(pWriter);
    }

    private void registerMetalBlockRecipes(Consumer<FinishedRecipe> pWriter) {
        MetalBlocks.BLOCKS.getEntries().forEach(block -> {
            String materialType = block.getId().getPath().replace("_block","");
            //TODO: Fix warning
            RegistryObject<Item> unpackedItem = IngotItems.ITEMS.getEntries().stream().filter(item -> item.getId().getPath().startsWith(materialType)).findFirst().get();
            ninePackingUnpackingRecipes(RecipeCategory.BUILDING_BLOCKS, block.get(),"from/ingot/", RecipeCategory.BUILDING_BLOCKS,unpackedItem.get(),"from/block/", pWriter);
        });
    }

    private void registerNuggetRecipes(Consumer<FinishedRecipe> pWriter) {
        NuggetItems.ITEMS.getEntries().forEach(nugget -> {
            String materialType = nugget.getId().getPath().replace("_nugget","");
            RegistryObject<Item> packedItem = IngotItems.ITEMS.getEntries().stream().filter(item -> item.getId().getPath().startsWith(materialType)).findFirst().get();
            ninePackingUnpackingRecipes(RecipeCategory.BUILDING_BLOCKS, packedItem.get(), "from/nugget/", RecipeCategory.BUILDING_BLOCKS,nugget.get(),"from/ingot/", pWriter);
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
            RegistryObject<Item> ingotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().startsWith(materialType+"_")).findFirst().get();

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
            RegistryObject<Item> ingotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().startsWith(materialType)).findFirst().get();
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


    private void ninePackingUnpackingRecipes(RecipeCategory packedCategory, ItemLike packedItem, String packedPath, RecipeCategory unpackedCategory, ItemLike unpackedItem, String unpackedPath, Consumer<FinishedRecipe> consumer) {
        //TODO: Make path dynamic so that this can be used for nuggets to ingots
        ShapedRecipeBuilder.shaped(packedCategory, packedItem)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', unpackedItem)
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(consumer, new ResourceLocation(ThisOreThat.MOD_ID, packedPath+ getItemName(packedItem)));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packedItem)
                .unlockedBy(getHasName(packedItem),has(packedItem))
                .save(consumer,new ResourceLocation(ThisOreThat.MOD_ID, unpackedPath + getItemName(unpackedItem)));
    }
}
