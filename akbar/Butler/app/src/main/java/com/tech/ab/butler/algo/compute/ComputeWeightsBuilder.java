package com.tech.ab.butler.algo.compute;

import com.tech.ab.butler.algo.entities.WeightPair;

/**
 * Created by shreenath on 15/1/17.
 */
public class ComputeWeightsBuilder {
    private Double[] spatialWeights;
    private WeightPair temporalWeights;
    private Double[] urgencyWeights;
    private Double inherentScoreWeight;
    private Double dependencyPenalty;
    private Double deadlineMissPenalty;

    public ComputeWeightsBuilder withSpatialWeights(Double[] array) {
        this.spatialWeights = array;
        return this;
    }

    public ComputeWeightsBuilder withTemporalWeights(double yes, double no) {
        this.temporalWeights = new WeightPair(yes, no);
        return this;
    }

    public ComputeWeightsBuilder withUrgencyWeights(Double[] array) {
        this.urgencyWeights = array;
        return this;
    }

    public ComputeWeightsBuilder withInherentScoreWeight(double w) {
        this.inherentScoreWeight = w;
        return this;
    }

    public ComputeWeightsBuilder withDependencyPenalty(double w) {
        this.dependencyPenalty= w;
        return this;
    }

    public ComputeWeightsBuilder withDeadlineMissPenalty(double w) {
        this.deadlineMissPenalty= w;
        return this;
    }

    public ComputeWeights build() {
        return new ComputeWeights(spatialWeights, temporalWeights, urgencyWeights, inherentScoreWeight, dependencyPenalty, deadlineMissPenalty);
    }

}
