package dev.splitwolf.thisorethat.datagen;

import dev.splitwolf.thisorethat.SmeltingEnabledCondition;
import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
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
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        registerMetalBlockRecipes(pWriter);
        registerSmeltingRecipes(pWriter);
        registerNuggetRecipes(pWriter);
    }
    private void registerMetalBlockRecipes(Consumer<FinishedRecipe> pWriter) {
        MetalBlocks.BLOCKS.getEntries().forEach(block -> {
            String materialType = block.getId().getPath().replace("_block","");
            Optional<RegistryObject<Item>> optUnpackedItem = IngotItems.ITEMS.getEntries().stream().filter(item -> item.getId().getPath().startsWith(materialType)).findFirst();
            optUnpackedItem.ifPresent(unpackedItem ->
                    ninePackingUnpackingRecipes(RecipeCategory.BUILDING_BLOCKS, block.get(), "from/ingot/", RecipeCategory.BUILDING_BLOCKS, unpackedItem.get(), "from/block/", pWriter));
        });
    }

    private void registerNuggetRecipes(Consumer<FinishedRecipe> pWriter) {
        NuggetItems.ITEMS.getEntries().forEach(nugget -> {
            String materialType = nugget.getId().getPath().replace("_nugget","");
            Optional<RegistryObject<Item>> optPackedItem = IngotItems.ITEMS.getEntries().stream().filter(item -> item.getId().getPath().startsWith(materialType)).findFirst();
            optPackedItem.ifPresent(packedItem ->
                    ninePackingUnpackingRecipes(RecipeCategory.BUILDING_BLOCKS, packedItem.get(), "from/nugget/", RecipeCategory.BUILDING_BLOCKS,nugget.get(),"from/ingot/", pWriter));
        });
    }

    private void registerSmeltingRecipes(Consumer<FinishedRecipe> pWriter) {
        //TODO: blasting
        OreBlocks.BLOCK_ITEMS.getEntries().forEach(item -> {
            String materialType = item.getId().getPath().replace("_ore","").replace("deepslate_","");
            if(materialType.equals("salt") || materialType.equals("sulfur"))
                return;
            Optional<RegistryObject<Item>> optIngotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().startsWith(materialType+"_")).findFirst();
            //TODO: Update Time and Exp
            //TODO: Fix naming
            optIngotItem.ifPresent(itemRegistryObject -> oreCook(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(item.get()), RecipeCategory.BUILDING_BLOCKS, itemRegistryObject.get(), 0.35f, 200, "smelting"));
        });

        RawOreItems.ITEMS.getEntries().forEach(item -> {
            String materialType = item.getId().getPath().replace("raw_","");
            if(materialType.equals("salt") || materialType.equals("sulfur"))
                return;
            Optional<RegistryObject<Item>> optIngotItem = IngotItems.ITEMS.getEntries().stream().filter(ingItem -> ingItem.getId().getPath().startsWith(materialType)).findFirst();
            //TODO: Fix naming
            optIngotItem.ifPresent(itemRegistryObject -> oreCook(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(item.get()), RecipeCategory.BUILDING_BLOCKS, itemRegistryObject.get(), 0.35f, 200, "smelting"));
        });

    }

    private static class ReturnRecipe implements Consumer<FinishedRecipe> {
        FinishedRecipe toReturn;

        @Override
        public void accept(FinishedRecipe finishedRecipe) {
            toReturn = finishedRecipe;
        }

        public FinishedRecipe getLast() {
            return toReturn;
        }
    }

    private void oreCook(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pRecipeName) {
        ReturnRecipe recipeReturner = new ReturnRecipe();
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
                    .save(recipeReturner, new ResourceLocation(ThisOreThat.MOD_ID, pRecipeName + "/" + getItemName(pResult) + "_from_" + getItemName(itemlike)));

            ConditionalRecipe.builder().addCondition(SmeltingEnabledCondition.INSTANCE)
                    .addRecipe(recipeReturner.getLast())
                    .build(pFinishedRecipeConsumer,recipeReturner.getLast().getId());

        }
    }

    @SuppressWarnings("SameParameterValue")
    private void ninePackingUnpackingRecipes(RecipeCategory packedCategory, ItemLike packedItem, String packedPath, RecipeCategory unpackedCategory, ItemLike unpackedItem, String unpackedPath, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(packedCategory, packedItem)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', unpackedItem)
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(consumer, new ResourceLocation(ThisOreThat.MOD_ID, packedPath + getItemName(packedItem)));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packedItem)
                .unlockedBy(getHasName(packedItem),has(packedItem))
                .save(consumer,new ResourceLocation(ThisOreThat.MOD_ID, unpackedPath + getItemName(unpackedItem)));
    }
}
