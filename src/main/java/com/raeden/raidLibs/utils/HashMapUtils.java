package com.raeden.raidLibs.utils;

import java.util.Map;

public class HashMapUtils {
    public static <K> K SmallestIntFromMap(Map<K, Integer> map) {
        K smallestKey = null;
        int smallestNum = Integer.MAX_VALUE;
        for(Map.Entry<K, Integer> entry : map.entrySet()) {
            if(entry.getValue() < smallestNum) {
                smallestNum = entry.getValue();
                smallestKey = entry.getKey();
            }
        }

        return smallestKey;
    }

    public static <K> K LargestIntFromMap(Map<K, Integer> map) {
        K largestKey = null;
        int largestNum = Integer.MIN_VALUE;
        for(Map.Entry<K, Integer> entry : map.entrySet()) {
            if(entry.getValue() < largestNum) {
                largestNum = entry.getValue();
                largestKey = entry.getKey();
            }
        }

        return largestKey;
    }
}
