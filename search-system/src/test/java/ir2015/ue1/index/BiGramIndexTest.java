package ir2015.ue1.index;

import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.io.File;
import java.util.*;

/**
 * BiGramIndex Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/28/2015</pre>
 */
public class BiGramIndexTest extends TestCase {
    private List<Newsgroup> docs;

    public BiGramIndexTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        docs = new ArrayList<Newsgroup>();
        String path = "../20_newsgroups_subset/alt.atheism";
        File folder = new File(path);
        System.out.println(folder.listFiles().length);
        for (int i = 0; i < 10; ++i) {
            File f = folder.listFiles()[i];
//        for (File f : folder.listFiles()) {
            if(f.isFile()) {
                docs.add(new NewsgroupTopicParser().parse(path + "/" + f.getName()));
            }
        }
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBiGramIndexInstance() throws Exception {
        BiGramIndex test = new BiGramIndex(docs);
        assert test.getClass().equals(BiGramIndex.class);
    }

    public static Test suite() {
        return new TestSuite(BiGramIndexTest.class);
    }
} 

