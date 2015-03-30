package ir2015.ue1.index;

import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * BagOfWordsIndex Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/28/2015</pre>
 */
public class BagOfWordsIndexTest extends TestCase {

    List<Newsgroup> docs;

    public BagOfWordsIndexTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        docs = new ArrayList<Newsgroup>();
        String path = "../20_newsgroups_subset/alt.atheism";
        File folder = new File(path);
        System.out.println(folder.listFiles().length);
        for (File f : folder.listFiles()) {
            if(f.isFile()) {
                docs.add(new NewsgroupTopicParser().parse(path + "/" + f.getName()));
            }
        }
//        for (int i = 0; i < 10; ++i) {
//            File f = folder.listFiles()[i];
//            if(f.isFile()) {
//                docs.add(new NewsgroupTopicParser().parse(path + "/" + f.getName()));
//            }
//        }
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /*
    public void testTokens() throws Exception {
        BagOfWordsIndex index = new BagOfWordsIndex(docs);
    }
    */

    public void testListSize() throws Exception {
        BagOfWordsIndex idx = new BagOfWordsIndex(docs);
        System.out.println(idx.getTextDictionary().size());
//        for (List<Integer> occurrence : idx.getTextOccurrences()) {
//            System.out.println(occurrence.toString());
//        }
//        System.out.println(idx.getXrefDict().toString());
//        for (List<Integer> occurrence : idx.getXrefOcc()) {
//            System.out.println(occurrence.toString());
//        }
    }

    public void testWriteToJSON() throws Exception {
        BagOfWordsIndex idx = new BagOfWordsIndex(docs);
        idx.writeToJSON("test");
    }

    public static Test suite() {
        return new TestSuite(BagOfWordsIndexTest.class);
    }
} 
