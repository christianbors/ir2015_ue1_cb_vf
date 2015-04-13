package ir2015.ue1;

import java.util.Map;

/**
 * Created by christianbors on 13/04/15.
 */
public class Experiments {
    private Map<String, Experiment> experimentMap;

    public Experiments(Map<String, Experiment> experiments) {
        this.experimentMap = experiments;
    }

    public String contains(Experiment searchExperiment) {
        String experimentName = "";
        for (Map.Entry<String, Experiment> entry : experimentMap.entrySet()) {
            Experiment experiment = entry.getValue();
            if (experiment.isBigram() == searchExperiment.isBigram() &&
                    experiment.isCaseFold() == searchExperiment.isCaseFold() &&
                    experiment.isRemoveStopwords() == searchExperiment.isRemoveStopwords() &&
                    experiment.isStemming() == searchExperiment.isStemming()) {
                experimentName = entry.getKey();
                break;
            }
        }
        return experimentName;
    }

    public String add(Experiment newExperiment) {
        String experimentName = "group4-experiment" + (experimentMap.size()+1);
        experimentMap.put(experimentName, newExperiment);
        return experimentName;
    }
}
