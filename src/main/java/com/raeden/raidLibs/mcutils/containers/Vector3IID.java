package com.raeden.raidLibs.mcutils.containers;

public class Vector3IID {
    private int x;
    private int y;
    private int z;
    private int id;
    public Vector3IID() {}
    public Vector3IID(int x, int y, int z, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public int getId() {return id;}
}
