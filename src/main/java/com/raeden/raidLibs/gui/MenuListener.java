package com.raeden.raidLibs.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.net.http.WebSocket;

public class MenuListener implements WebSocket.Listener {
    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu) {
            e.setCancelled(true);
            if(e.getCurrentItem() == null) return;

            Menu menu = (Menu) holder;
            menu.handleClicks(e);
        }
    }
}
