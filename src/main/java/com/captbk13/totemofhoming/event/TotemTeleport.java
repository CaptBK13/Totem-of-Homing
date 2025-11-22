package com.captbk13.totemofhoming.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class TotemTeleport {
    public static void totem_teleport(ServerPlayer player) {
        // 获取安全复活点
        DimensionTransition target = player.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING);
        ServerLevel level = target.newLevel();
        Vec3 pos = target.pos();

        // 传送玩家
        player.teleportTo(level, pos.x, pos.y, pos.z, target.yRot(), target.xRot());
    }
}
