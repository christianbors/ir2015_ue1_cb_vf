package ir2015.ue1.tokenizer;

/**
 * Created by christianbors on 26/03/15.
 */
public class TestTokenizer {

    public boolean testTokenizer() {
        String[] result = "this is a test".split("\\s");
        for(int x=0; x<result.length; x++)
            System.out.println("result[x]");
        return true;
    }
}
