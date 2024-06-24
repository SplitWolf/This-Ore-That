package dev.splitwolf.thisorethat;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ThisOreThat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThisOreThatConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue enableSmeltingRecipes = BUILDER
            .comment("Whether to add the smelting recipes for the ores or not")
            .define("enableSmeltingRecipes", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
