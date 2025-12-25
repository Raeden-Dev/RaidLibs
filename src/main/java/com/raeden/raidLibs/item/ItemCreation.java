package com.raeden.raidLibs.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

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
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setLore(List.of(meta.getLore() + lore));
        item.setItemMeta(meta);

        return item;
    }
    // Adding persistent data container to item
    public static ItemStack addDataToItem(JavaPlugin plugin, ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        NamespacedKey key = new NamespacedKey(plugin, name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, name);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addDataToItem(JavaPlugin plugin, ItemStack item, String name, int amount) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        NamespacedKey key = new NamespacedKey(plugin, name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, amount);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addDataToItem(JavaPlugin plugin, ItemStack item, String name, boolean status) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        NamespacedKey key = new NamespacedKey(plugin, name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, status);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addDataToItem(JavaPlugin plugin, ItemStack item, String name, float amount) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        NamespacedKey key = new NamespacedKey(plugin, name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.FLOAT, amount);
        item.setItemMeta(meta);
        return item;
    }

}
