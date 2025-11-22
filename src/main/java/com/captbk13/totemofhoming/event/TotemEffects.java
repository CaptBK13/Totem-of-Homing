package com.captbk13.totemofhoming.event;

import com.captbk13.totemofhoming.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class TotemEffects {
    // 图腾列表，可扩展
    private static final List<Item> TOTEM_LIST = List.of(
            ModItems.NORMAL_TOTEM_OF_HOMING.get());

    // 查找图腾
    public static Optional<ItemStack> findTotem(ServerPlayer player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();
        if (TOTEM_LIST.contains(main.getItem())) return Optional.of(main);
        if (TOTEM_LIST.contains(off.getItem()))  return Optional.of(off);
        return Optional.empty();
    }

    //图腾效果
    public static void totem_effects(ServerPlayer player) {
        player.setHealth(1.0F);
        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));

        // 客户端播放图腾动画
        Minecraft.getInstance().gameRenderer.displayItemActivation(
                new ItemStack(ModItems.NORMAL_TOTEM_OF_HOMING.get())
        );
        // 音效
        if (player.level() instanceof ServerLevel serverLevel) {
            //末影珍珠
            serverLevel.playSound(null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS,
                    1.3F, 1.0F
            );
            //不死图腾
            serverLevel.playSound(null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TOTEM_USE, SoundSource.PLAYERS,
                    0.3F, 1.0F
            );

            for (int i=0; i < 5; i++) {
                serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    player.getX() + 0,
                    player.getY() + player.getBbHeight()/2,
                    player.getZ() + 0,
                    30,1,1,1,0.0
            );
            }
        }
    }
}
