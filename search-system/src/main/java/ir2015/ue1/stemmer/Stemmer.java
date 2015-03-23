package ir2015.ue1.stemmer;

import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by christianbors on 23/03/15.
 */
public class Stemmer {

    public String stemPorter(String word) {
        PorterStemmer stemmer = new PorterStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }

    public String stemEnglishStemmer(String word) {
        EnglishStemmer stemmer = new EnglishStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }

    public String stemEnglishAnalyzer(String word) {
        throw new NotImplementedException();
    }
}
