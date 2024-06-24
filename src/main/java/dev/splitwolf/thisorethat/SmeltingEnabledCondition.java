package dev.splitwolf.thisorethat;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class SmeltingEnabledCondition implements ICondition {

    public static final SmeltingEnabledCondition INSTANCE = new SmeltingEnabledCondition();
    private static final ResourceLocation NAME =new ResourceLocation(ThisOreThat.MOD_ID, "smelting_enabled");

    private SmeltingEnabledCondition() {}

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(IContext context) {
        return ThisOreThatConfig.enableSmeltingRecipes.get();
    }

    @Override
    public String toString()
    {
        return ThisOreThatConfig.enableSmeltingRecipes.get().toString();
    }

    public static class Serializer implements IConditionSerializer<SmeltingEnabledCondition> {

        @Override
        public void write(JsonObject json, SmeltingEnabledCondition value) {}


        @Override
        public SmeltingEnabledCondition read(JsonObject json) {
            return SmeltingEnabledCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID() {
            return SmeltingEnabledCondition.NAME;
        }
    }
}
