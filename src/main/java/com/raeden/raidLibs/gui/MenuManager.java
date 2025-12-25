package com.raeden.raidLibs.gui;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {

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

    // Turns "myGui - pg1" --> "myGui" assuming " - pg" is the pageSeq
    private String stripPage(String title, String pageSequence) {
        if(!title.contains(pageSequence)) return title;

        int startIndex = title.indexOf(pageSequence);
        if(startIndex != -1) {
            int endIndex = startIndex + pageSequence.length();
            String result = title.substring(endIndex);

            title = title.replace(result, "");
            title = title.replace(pageSequence, "");
        }
        return title;
    }


}