package com.raeden.raidLibs.general_mc_utils;

import org.bukkit.entity.Player;

public class PermChecker {
    public static boolean playerHasPerm(Player player, String permission) {
        if(player.isOp() || player.hasPermission(permission)) {
            return true;
        }
        return false;
    }
}
