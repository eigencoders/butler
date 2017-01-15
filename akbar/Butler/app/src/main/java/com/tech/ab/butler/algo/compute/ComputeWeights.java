package com.tech.ab.butler.algo.compute;


import com.tech.ab.butler.algo.entities.WeightPair;

import lombok.AllArgsConstructor;

/**
 * Created by shreenath on 15/1/17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
public class ComputeWeights {
    public WeightPair spatialWeights;
    public WeightPair temporalWeights;
    public Double[] urgencyWeights;
    public double inherentScoreWeight;
    public double dependencyPenalty;
//    WeightPair spatialWeights;
//    WeightPair spatialWeights;
    
}
