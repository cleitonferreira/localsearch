package com.hse.ls.guided;

import com.hse.ls.common.entity.LocalSearch;
import com.hse.ls.common.entity.LocalSearchContext;
import com.hse.ls.common.entity.Solution;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Classe - Busca Local Guiada
 */
public class GuidedLocalSearch extends LocalSearch {
    private int[] penalties; //penalidades

    public GuidedLocalSearch(final LocalSearchContext context) {
        super(context);
        penalties = new int[context.problem.dim * context.problem.dim];
    }

    /**
     * Busca local (GLS)
     * @param prev
     * @return melhor local
     */
    private GuidedSolution localSearch(GuidedSolution prev) {
        int[] locations =  ArrayUtils.clone(prev.locations); // realiza um clone da solução

        GuidedSolution localBest = new GuidedSolution(prev); // declara o melhor local
        GuidedSolution current; // solução atual

        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                ArrayUtils.swap(locations, i, j); // troca as posições de dois elementos em uma matriz.
                current = new GuidedSolution(context.problem, ArrayUtils.clone(locations), penalties);
                if (current.augmentedCost < localBest.augmentedCost) current.copyTo(localBest); // se o custo da solucao atual for menor do que melhor local
                context.storeBest(current); // encontrar a solução melhor
                ArrayUtils.swap(locations, i, j); // troca as posições de dois elementos em uma matriz.
            }
        }
        return localBest; // melhor local
    }

    /**
     * @return melhor
     */
    @Override
    public Solution solve() {
        GuidedSolution current = new GuidedSolution(context.problem, penalties);
        context.best = new GuidedSolution(current);

        while (!context.shouldBreak()) {
            current = localSearch(current);
            current.calculateUtility(penalties);
            updatePenalties(current);
        }

        return context.best;
    }

    /**
     * Alterar a penalidade da solução atual
     * @param current
     */
    private void updatePenalties(final GuidedSolution current) {
        double max = Arrays.stream(current.features)
                .parallel()
                .mapToDouble(f -> f.utility)
                .max().getAsDouble();

        IntStream.range(0, current.features.length)
                .parallel()
                .filter(ix -> current.features[ix].utility == max)
                .forEach(ix -> penalties[ix]++);
    }
}
