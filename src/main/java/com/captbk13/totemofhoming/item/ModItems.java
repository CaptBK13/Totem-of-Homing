package com.captbk13.totemofhoming.item;

import com.captbk13.totemofhoming.TotemofHoming;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(TotemofHoming.MODID);

    public static final DeferredItem<Item> NORMAL_TOTEM_OF_HOMING =
            ITEMS.register("normal_totem_of_homing",
                    () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
