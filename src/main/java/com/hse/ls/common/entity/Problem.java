package com.hse.ls.common.entity;

/**
 * Entidade descreve os parâmetros de entrada para resolver o problema de atribuição quadrática
 */
public class Problem {

    public final int dim; // dimensão

    public final int[][] distance; // distancia

    public final int[][] flow; // fluxo

    public Problem(int dim, int[][] distance, int[][] flow) {
        this.dim = dim;
        this.distance = distance;
        this.flow = flow;
    }
}
