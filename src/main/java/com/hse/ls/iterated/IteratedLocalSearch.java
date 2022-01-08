package com.hse.ls.iterated;

import com.hse.ls.common.entity.LocalSearch;
import com.hse.ls.common.entity.LocalSearchContext;
import com.hse.ls.common.entity.Solution;
import org.apache.commons.lang3.ArrayUtils;

public class IteratedLocalSearch extends LocalSearch {

    public IteratedLocalSearch(final LocalSearchContext context) {
        super(context);
    }

    /**
     * Busca local (ILS)
     * @param s - Solução
     */
    private void localSearch(Solution s) {
        int[] locations =  ArrayUtils.clone(s.locations);
        Solution current;

        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                ArrayUtils.swap(locations, i, j); // troca as posições de dois elementos em uma matriz.
                current = new Solution(context.problem, ArrayUtils.clone(locations));
                context.storeBest(current); // encontrar a solução melhor
                ArrayUtils.swap(locations, i, j);
            }
        }
    }

    /**
     * @return melhor
     */
    @Override
    public Solution solve() {
        Solution current = new Solution(context.problem);
        context.best = new Solution(current);

        while (!context.shouldBreak()){
            perturb(current);
            localSearch(current);
        }

        return context.best;
    }
}

