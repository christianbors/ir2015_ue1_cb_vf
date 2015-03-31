package ir2015.ue1.model;

import com.google.gson.Gson;
import ir2015.ue1.parser.NewsgroupTopicParser;
import junit.framework.Test;
import junit.framework.TestSuite; 
import junit.framework.TestCase;

import java.io.FileReader;

/** 
* Newsgroup Tester. 
* 
* @author <Authors name> 
* @since <pre>03/28/2015</pre> 
* @version 1.0 
*/ 
public class NewsgroupTest extends TestCase { 
public NewsgroupTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp();
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 



public static Test suite() { 
return new TestSuite(NewsgroupTest.class); 
}

    public void testGsonSerialize() throws Exception {
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();
        Newsgroup ng = ntp.parse("../20_newsgroups_subset/alt.atheism/51120");
        String ngJson = ng.toJson();

        Newsgroup ngFromJson = new Gson().fromJson(ngJson, Newsgroup.class);
        assertEquals(ng, ngFromJson);
    }
}
