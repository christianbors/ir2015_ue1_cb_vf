package ir2015.ue1;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by christianbors on 13/04/15.
 */
public class Experiment {

    private boolean stemming;
    private boolean caseFold;
    private boolean removeStopwords;
    private boolean bigram;

    public Experiment(boolean stemming, boolean caseFold, boolean removeStopwords, boolean bigram) {
        this.stemming = stemming;
        this.caseFold = caseFold;
        this.removeStopwords = removeStopwords;
        this.bigram = bigram;
    }

    public boolean isStemming() {
        return stemming;
    }

    public boolean isCaseFold() {
        return caseFold;
    }

    public boolean isRemoveStopwords() {
        return removeStopwords;
    }

    public boolean isBigram() {
        return bigram;
    }
}