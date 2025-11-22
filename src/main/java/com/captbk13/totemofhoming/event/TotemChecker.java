package com.captbk13.totemofhoming.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "totem_of_homing")
public class TotemChecker {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {

        // 判断是不是玩家
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // 玩家是否持有图腾
        if (TotemEffects.findTotem(player).isEmpty()) return;

        // 判断是否无法阻挡的伤害，如/kill，虚空
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
        // 获取伤害和生命
        float damage = event.getNewDamage();
        float health = player.getHealth();
        if (health - damage >= 0.0F)return;

        // 触发传送到重生点
        TotemTeleport.totem_teleport(player);
        // 触发类原版图腾效果（回血、Buff、动画、音效）
        TotemEffects.totem_effects(player);
        // 消耗图腾
        TotemEffects.findTotem(player).get().shrink(1);  // 消耗图腾
        }
    }


