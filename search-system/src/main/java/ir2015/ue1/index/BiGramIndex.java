package ir2015.ue1.index;

import ir2015.ue1.model.Newsgroup;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by christianbors on 26/03/15.
 */
public class BiGramIndex {

    private CharTermAttribute charTermAttribute;
    private NGramTokenizer bigramTokenizer;

    public BiGramIndex(List<Newsgroup> documents) {
        StringReader r = new StringReader(documents.get(0).getText());
        bigramTokenizer = new NGramTokenizer(2, 2);
        try {
            this.charTermAttribute = bigramTokenizer.addAttribute(CharTermAttribute.class);
            bigramTokenizer.setReader(r);
            bigramTokenizer.reset();
            System.out.println(bigramTokenizer.toString());
            while (bigramTokenizer.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            bigramTokenizer.clearAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
