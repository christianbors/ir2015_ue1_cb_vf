package ir2015.ue1.parser;

import ir2015.ue1.model.Newsgroup;
import junit.framework.TestCase;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NewsgroupTopicParserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testFoo() throws Exception {
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();
        Newsgroup ng = ntp.parse("../20_newsgroups_subset/alt.atheism/51120");
        System.out.println(ng.toString());
    }
}