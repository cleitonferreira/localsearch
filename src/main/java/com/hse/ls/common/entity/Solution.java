package com.hse.ls.common.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    protected Problem p;

    public int[] locations;

    public int cost;

    public Solution(Solution s) {
        this.locations = ArrayUtils.clone(s.locations);
        this.cost = s.cost;
    }

    public Solution(Problem p) {
        this.p = p;
        this.locations = IntStream.rangeClosed(0, p.dim - 1).toArray();
        this.cost = evaluate();
    }

    public Solution(Problem p, int[] locations) {
        this.p = p;
        this.locations = locations;
        this.cost = evaluate();
    }

    public void copyTo(Solution s) {
        s.locations = ArrayUtils.clone(this.locations);
        s.cost = this.cost;
    }

    public int evaluate() {
        cost = 0;
        int n = locations.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = locations[i];
                int b = locations[j];
                cost += p.flow[a][b] * p.distance[i][j];
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        return Arrays.toString(locations);
    }
}

