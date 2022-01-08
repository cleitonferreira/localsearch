package com.hse.ls.guided;

import com.hse.ls.common.entity.Problem;
import com.hse.ls.common.entity.Solution;
import org.apache.commons.lang3.ArrayUtils;

@SuppressWarnings("WeakerAccess")
public class GuidedSolution extends Solution {
    /**
     * Matriz com indicadores de recursos
     * features [i] .exist = true, significa que a planta i está definida no lugar j;
     */
    public Feature[] features;

    /**
     * Número de recursos na solução atual
     */
    public int numberOfFeatures = 0;

    /**
     * Parâmetro normalizado metaheurístico
     */
    public double lambda = 0.0;

    public double augmentedCost = 0.0;

    public GuidedSolution(GuidedSolution s) {
        super(s.p);
        this.locations = ArrayUtils.clone(s.locations);
        this.cost = s.cost;
        this.augmentedCost = s.augmentedCost;
        this.features = ArrayUtils.clone(s.features);
        this.lambda = s.lambda;
        this.numberOfFeatures = s.numberOfFeatures;
    }

    public GuidedSolution(Problem p, final int[] penalties) {
        super(p);
        this.features = new Feature[p.dim * p.dim];

        updateFeatures();
        calculateFeaturesCost();

        calculateLambda();
        evaluateAugmentedCost(penalties);
    }

    public GuidedSolution(Problem p, int[] locations, final int[] penalties) {
        super(p, locations);
        this.features = new Feature[p.dim * p.dim];

        updateFeatures();
        calculateFeaturesCost();

        calculateLambda();
        evaluateAugmentedCost(penalties);
    }

    private void updateFeatures() {
        numberOfFeatures = 0;
        for (int i = 0; i < features.length; i++) {
            features[i] = new Feature();
        }

        for (int i = 0; i < locations.length; i++) {
            int l = locations[i]; // location at i place;
            int pos = l * locations.length + i;
            features[pos].plant = l;
            features[pos].city = i;
            features[pos].exist = true;

            numberOfFeatures++;
        }
    }

    private void calculateFeaturesCost() {
        for (Feature feature : features) {
            if (feature.exist) {
                feature.cost = 0.0;
                for (int j = 0; j < locations.length; j++) {
                    int a = feature.plant;
                    int b = locations[j];
                    feature.cost += p.flow[a][b] * p.distance[feature.city][j];
                }
            }
        }
    }

    private double calculateLambda() {
        lambda = (double) cost / numberOfFeatures;
        return lambda;
    }

    private double evaluateAugmentedCost(int[] penalties) {
        double totalAugment = 0.0;
        for (int i = 0; i < features.length; i++) {
            if (features[i].exist) {
                totalAugment += features[i].cost * penalties[i];
            }
        }

        this.augmentedCost = this.cost + this.lambda * totalAugment;
        return augmentedCost;
    }

    public void calculateUtility(int[] penalties) {
        for (int i = 0; i < features.length; i++) {
            if (features[i].exist) {
                features[i].utility = features[i].cost / (penalties[i] + 1);
            }
        }
    }

    public void copyTo(GuidedSolution s) {
        s.locations = ArrayUtils.clone(this.locations);
        s.cost = this.cost;
        s.augmentedCost = this.augmentedCost;
        s.features = ArrayUtils.clone(this.features);
        s.lambda = this.lambda;
        s.numberOfFeatures = this.numberOfFeatures;
    }
}
