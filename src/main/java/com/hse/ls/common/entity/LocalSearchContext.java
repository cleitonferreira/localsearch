package com.hse.ls.common.entity;

@SuppressWarnings("WeakerAccess")
public class LocalSearchContext {
    public final int ITERATIONS_WITHOUT_IMPROVEMENTS;
    public int currentIteration;
    public final Problem problem;

    public Solution best;

    public LocalSearchContext(final Problem problem, int ITERATIONS_WITHOUT_IMPROVEMENTS) {
        this.problem = problem;
        this.ITERATIONS_WITHOUT_IMPROVEMENTS = ITERATIONS_WITHOUT_IMPROVEMENTS;
        currentIteration = 0;
    }

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
