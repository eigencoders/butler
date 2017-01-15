package com.tech.ab.butler.algo.compute;

import com.tech.ab.butler.algo.entities.WeightPair;

/**
 * Created by shreenath on 15/1/17.
 */
public class ComputeWeightsBuilder {
    WeightPair spatialWeights;
    WeightPair temporalWeights;
    Double[] urgencyWeights;
    Double inherentScoreWeight;
    Double dependencyPenalty;

    public ComputeWeightsBuilder withSpatialWeights(double yes, double no) {
        this.spatialWeights = new WeightPair(yes, no);
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

    public ComputeWeights build() {
        return new ComputeWeights(spatialWeights, temporalWeights, urgencyWeights, inherentScoreWeight, dependencyPenalty);
    }

}
