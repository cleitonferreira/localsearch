package com.hse.ls.common.entity;

/**
 * Entidade descreve os parâmetros de entrada para resolver o problema de atribuição quadrática
 */
public class Problem {

    public final int dim;

    public final int[][] distance;

    public final int[][] flow;

    public Problem(int dim, int[][] distance, int[][] flow) {
        this.dim = dim;
        this.distance = distance;
        this.flow = flow;
    }
}
