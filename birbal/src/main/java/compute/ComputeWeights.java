package compute;

import entities.WeightPair;
import lombok.AllArgsConstructor;

/**
 * Created by shreenath on 15/1/17.
 */
@AllArgsConstructor
public class ComputeWeights {
    WeightPair spatialWeights;
    WeightPair temporalWeights;
    Double[] urgencyWeights;
    double inherentScoreWeight;
    double dependencyPenalty;
//    WeightPair spatialWeights;
//    WeightPair spatialWeights;
    
}
