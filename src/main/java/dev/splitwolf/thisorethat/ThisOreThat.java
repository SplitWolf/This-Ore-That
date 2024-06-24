package dev.splitwolf.thisorethat;

import com.mojang.logging.LogUtils;
import dev.splitwolf.thisorethat.block.MetalBlocks;
import dev.splitwolf.thisorethat.block.OreBlocks;
import dev.splitwolf.thisorethat.block.RawOreBlocks;
import dev.splitwolf.thisorethat.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ThisOreThat.MOD_ID)
public class ThisOreThat {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "thisorethat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ThisOreThat() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CreativeModeTabs.register(modEventBus);

        IngotItems.register(modEventBus);

        RawOreItems.register(modEventBus);

        NuggetItems.register(modEventBus);
        MetalBlocks.register(modEventBus);

        OreBlocks.register(modEventBus);

        RawOreBlocks.register(modEventBus);

        DustItems.register(modEventBus);
        GearItems.register(modEventBus);
        SheetItems.register(modEventBus);


        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::registerCondition);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ThisOreThatConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void registerCondition(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS,
                helper -> CraftingHelper.register(new SmeltingEnabledCondition.Serializer()));
    }

}
