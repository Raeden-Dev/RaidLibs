package com.raeden.raidLibs.general_mc_utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QueueSystem<K, V> {
    private final JavaPlugin plugin;
    private final Runnable runnable;
    private int queueDelayTime;
    private boolean isQueueOn;
    private final boolean hasStartDelay;
    private boolean isSmartQueue;
    private LinkedHashMap<K, V> queueList;
    private BukkitTask queueRunnable;

    public QueueSystem(JavaPlugin plugin, Runnable runnable, int queueDelayTime, boolean hasStartDelay, boolean isQueueOn, boolean isSmartQueue, LinkedHashMap<K, V> queueList) {
        this.plugin = plugin;
        this.runnable = runnable;
        this.queueDelayTime = queueDelayTime;
        this.isQueueOn = isQueueOn;
        this.hasStartDelay = hasStartDelay;
        this.isSmartQueue = isSmartQueue;
        this.queueList = queueList;

        if(isQueueOn) {
            runQueue();
        }
    }

    public void runQueue() {
        if(!isQueueOn) return;

        queueRunnable = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(!queueList.isEmpty()) {
                if(runnable != null) {
                    runnable.run();
                }

                List<K> queueListKeys = getQueueKeyList();
                removeFromQueue(queueListKeys.getFirst());
            } else if(isSmartQueue) {
                stopQueue();
            }

        }, hasStartDelay ? queueDelayTime : 0L, queueDelayTime * 20L);
    }

    public void startQueue() {
        if(!isQueueOn) {
            runQueue();
            isQueueOn = true;
        } else {
            Logger.infoLog(plugin.getName(), "Queue is already running!");
        }
    }

    public void stopQueue() {
        if(isQueueOn) {
            queueRunnable.cancel();
            isQueueOn = false;
        } else {
            Logger.infoLog(plugin.getName(), "Queue is not running!");
        }
    }

    public void getQueueKey(K key) {
        queueList.get(key);
    }

    public void addToQueue(K key, V value) {
        queueList.put(key, value);
        if(isSmartQueue) {
            startQueue();
        }
    }

    public void addFirstToQueue(K key, V value) {
        queueList.putFirst(key, value);
        if(isSmartQueue) {
            startQueue();
        }
    }

    public void addLastToQueue(K key, V value) {
        queueList.putLast(key, value);
        if(isSmartQueue) {
            startQueue();
        }
    }

    public void removeFromQueue(K key) {
        queueList.remove(key);
        if(isSmartQueue && queueList.isEmpty()) {
            stopQueue();
        }
    }

    public List<K> getQueueKeyList() {
        List<K> queueKeyList = new ArrayList<>(queueList.keySet());
        return queueKeyList.isEmpty() ? queueKeyList : null;
    }

    public boolean isQueueOn() {return isQueueOn;}
    public void setQueueOn(boolean queueOn) {isQueueOn = queueOn;}

    public LinkedHashMap<K, V> getQueueList() {return queueList;}
    public void setQueueList(LinkedHashMap<K, V> queueList) {this.queueList = queueList;}

    public int getQueueDelayTime() {return queueDelayTime;}
    public void setQueueDelayTime(int queueDelayTime) {this.queueDelayTime = queueDelayTime;}

    public BukkitTask getQueueRunnable() {return queueRunnable;}

    public void setQueueRunnable(BukkitTask queueRunnable) {this.queueRunnable = queueRunnable;}

    public Runnable getRunnable() {return runnable;}

    public boolean isSmartQueue(){ return isSmartQueue;}
    public void setSmartQueue(boolean isToggle){isSmartQueue = isToggle;}
}
