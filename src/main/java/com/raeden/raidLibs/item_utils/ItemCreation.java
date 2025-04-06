package com.raeden.raidLibs.item_utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemCreation {

    public static ItemStack createBasicItem(Material material, String displayName, String lore) {
        ItemStack myItem = new ItemStack(Objects.requireNonNull(material));
        ItemMeta myItemMeta = myItem.getItemMeta();
        Objects.requireNonNull(myItemMeta).setDisplayName(displayName);
        if(lore != null) {
            myItemMeta.setLore(List.of(lore));
        }

        myItem.setItemMeta(myItemMeta);

        return myItem;
    }
    public static ItemStack addLoreToItem(ItemStack item, String lore) {
        if (item == null) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        meta.setLore(List.of(meta.getLore() + lore));
        item.setItemMeta(meta);

        return item;
    }

}
