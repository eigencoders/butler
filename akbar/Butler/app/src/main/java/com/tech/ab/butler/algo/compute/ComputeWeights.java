package com.tech.ab.butler.algo.compute;

import com.tech.ab.butler.algo.entities.WeightPair;
import lombok.AllArgsConstructor;

/**
 * Created by shreenath on 15/1/17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
public class ComputeWeights {
    Double[] spatialWeights;
    WeightPair temporalWeights;
    Double[] urgencyWeights;
    double inherentScoreWeight;
    double dependencyPenalty;
    double deadlineMissPenalty;
//    WeightPair spatialWeights;
//    WeightPair spatialWeights;
    
}
