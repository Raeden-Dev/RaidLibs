package com.raeden.raidLibs._utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    public static int randNumFromRange(int max, int min) {
        Random random = new Random();
        return random.nextInt((max - min + 1) + min);
    }

    public static String RandStringFromList(List<String> stringList) {
        Random random = new Random();
        int random_index = random.nextInt(stringList.size());
        return stringList.get(random_index);
    }

    public static <K> K pickRandFromWeightMap(LinkedHashMap<K, Double> weightMap) {
        double totalWeight = 0;
        for(double weight : weightMap.values()) {
            totalWeight += weight;
        }

        double rand = Math.random() * totalWeight;
        double cumulative = 0;

        for (Map.Entry<K, Double> entry : weightMap.entrySet()) {
            cumulative += entry.getValue();
            if (rand <= cumulative) {
                return entry.getKey();
            }
        }

        return null;
    }

    public static <K> LinkedHashMap<K, Double> normalizeWeightsInMap(LinkedHashMap<K, Double> weightMap, int max) {
        double totalWeight = 0;
        LinkedHashMap<K, Double> normalizedMap = new LinkedHashMap<>();
        for(Map.Entry<K, Double> entry : weightMap.entrySet()) {
            totalWeight += entry.getValue();
        }

        for(Map.Entry<K, Double> entry : weightMap.entrySet()) {
            double value = (entry.getValue() / totalWeight) * max;
            normalizedMap.put(entry.getKey(), value);
        }

        return normalizedMap;
    }

}
