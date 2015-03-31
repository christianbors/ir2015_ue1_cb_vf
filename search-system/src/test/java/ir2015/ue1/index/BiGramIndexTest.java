package ir2015.ue1.index;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* BiGramIndex Tester. 
* 
* @author <Authors name> 
* @since <pre>03/28/2015</pre> 
* @version 1.0 
*/ 
public class BiGramIndexTest extends TestCase { 
public BiGramIndexTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
}

public static Test suite() { 
return new TestSuite(BiGramIndexTest.class); 
} 
} 

