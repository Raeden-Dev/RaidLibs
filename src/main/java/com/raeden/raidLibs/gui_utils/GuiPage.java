package com.raeden.raidLibs.gui_utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GuiPage {

    // Spaces is basically number of items per UI, (MAX 52 if you use next/prev btns only)
    public static List<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces) {
        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;

        List<ItemStack> newItems = new ArrayList<>();

        for (int i = lowerBound; i < upperBound; i++) {
            try {
                newItems.add(items.get(i));
            } catch (IndexOutOfBoundsException x) {
                break;
            }
        }

        return newItems;
    }

    public static boolean isPageValid(List<ItemStack> items, int page, int spaces) {
        if (page <= 0) { return false; }

        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;

        return items.size() > lowerBound;
    }

    public static ItemStack createPlayerHeadItem(String skullURL, String displayName, String lore) {
        ItemStack myItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) myItem.getItemMeta();

        UUID uuid = UUID.randomUUID();
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(uuid);
        PlayerTextures playerTextures = playerProfile.getTextures();

        try {
            playerTextures.setSkin(new URL(skullURL));
        } catch (Exception e) {
            e.printStackTrace();
        }

        playerProfile.setTextures(playerTextures);
        Objects.requireNonNull(skullMeta).setOwnerProfile(playerProfile);
        myItem.setItemMeta(skullMeta);

        ItemMeta myItemMeta = myItem.getItemMeta();
        Objects.requireNonNull(myItemMeta).setDisplayName(displayName);
        myItemMeta.setLore(List.of(ChatColor.GRAY.toString() + ChatColor.ITALIC + lore));
        myItem.setItemMeta(myItemMeta);

        return myItem;
    }
}