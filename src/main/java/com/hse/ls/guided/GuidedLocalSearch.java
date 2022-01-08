package com.hse.ls.guided;

import com.hse.ls.common.entity.LocalSearch;
import com.hse.ls.common.entity.LocalSearchContext;
import com.hse.ls.common.entity.Solution;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GuidedLocalSearch extends LocalSearch {
    private int[] penalties;

    public GuidedLocalSearch(final LocalSearchContext context) {
        super(context);
        penalties = new int[context.problem.dim * context.problem.dim];
    }

    private GuidedSolution localSearch(GuidedSolution prev) {
        int[] locations =  ArrayUtils.clone(prev.locations);

        GuidedSolution localBest = new GuidedSolution(prev);
        GuidedSolution current;

        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                ArrayUtils.swap(locations, i, j);
                current = new GuidedSolution(context.problem, ArrayUtils.clone(locations), penalties);
                if (current.augmentedCost < localBest.augmentedCost) current.copyTo(localBest);
                context.storeBest(current);
                ArrayUtils.swap(locations, i, j);
            }
        }
        return localBest;
    }

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
