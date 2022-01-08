package com.hse.ls.common.entity;

import org.apache.commons.math3.util.MathArrays;

public abstract class LocalSearch {
    protected final LocalSearchContext context;

    public LocalSearch(final LocalSearchContext context) {
        this.context = context;
    }

    protected void perturb(Solution s) {
        MathArrays.shuffle(s.locations);
        s.evaluate();
    }

    public abstract Solution solve();
}
