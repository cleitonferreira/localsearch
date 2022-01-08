package com.hse.ls.common.entity;

@SuppressWarnings("WeakerAccess")
public class LocalSearchContext {
    public final int ITERATIONS_WITHOUT_IMPROVEMENTS; //ITERAÇÕES SEM MELHORIAS
    public int currentIteration; //Iteração atual
    public final Problem problem; //problema

    public Solution best; // Melhor solução

    /**
     * Contexto da classe. Problema, ITERAÇÕES SEM MELHORIAS
     */
    public LocalSearchContext(final Problem problem, int ITERATIONS_WITHOUT_IMPROVEMENTS) {
        this.problem = problem;
        this.ITERATIONS_WITHOUT_IMPROVEMENTS = ITERATIONS_WITHOUT_IMPROVEMENTS;
        currentIteration = 0;
    }

    /**
     * encontrar a solução melhor
     */
    public void storeBest(Solution current) {
        if (current.cost < best.cost) {
            current.copyTo(best);
            currentIteration = 0;
        }
    }

    public boolean shouldBreak() {
        return ITERATIONS_WITHOUT_IMPROVEMENTS < currentIteration++;
    }
}
