package com.hse.ls.guided;

@SuppressWarnings("WeakerAccess")
public class Feature {
    public int plant;
    public int city;

    public boolean exist;
    public double cost;
    public double utility ;

    public Feature() {
        this.exist = false;
        this.cost = 0.0;
        this.city = -1;
        this.plant = -1;
        this.utility = 0.0;
    }
}
