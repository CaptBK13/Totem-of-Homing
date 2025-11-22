package com.captbk13.totemofhoming.item;

import com.captbk13.totemofhoming.TotemofHoming;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TotemofHoming.MODID);

    public static final Supplier<CreativeModeTab> Totem_of_Homing_Tab =
            CREATIVE_MODE_TABS.register("totem_of_homing_tab", CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.NORMAL_TOTEM_OF_HOMING.get()))
                    .title(Component.translatable("itemGroup.totem_of_homing_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.NORMAL_TOTEM_OF_HOMING);
                    })::build);
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

