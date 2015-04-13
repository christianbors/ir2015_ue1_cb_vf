package ir2015.ue1;

import ir2015.ue1.index.BagOfWordsIndex;
import ir2015.ue1.index.BiGramIndex;
import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private Map<String, Newsgroup> docs;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testZip() throws Exception {
        docs = new HashMap<String, Newsgroup>();
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();
        String path = "../20_newsgroups_subset/alt.atheism";
        File folder = new File(path);
        System.out.println(folder.listFiles().length);
        for (int i = 0; i < 10; ++i) {
            File f = folder.listFiles()[i];
//        for (File f : folder.listFiles()) {
            if(f.isFile()) {
                Newsgroup ng = ntp.parse(path + "/" + f.getName());
                ntp.tokenizeText(ng);
                docs.put(f.getName(), ng);
            }
        }
        BiGramIndex bg = new BiGramIndex(docs);
        BagOfWordsIndex bow = new BagOfWordsIndex(docs);
        App.zipIndex("test.zip", bg, "bigram.json", bow, "bow.json");
    }
}
