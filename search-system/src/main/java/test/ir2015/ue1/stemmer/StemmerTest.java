package test.ir2015.ue1.stemmer; 

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* Stemmer Tester. 
* 
* @author <Authors name> 
* @since <pre>03/23/2015</pre> 
* @version 1.0 
*/ 
public class StemmerTest extends TestCase {
public StemmerTest(String name) { 
super(name);
} 

public void setUp() throws Exception { 
super.setUp();
}

public void tearDown() throws Exception { 
super.tearDown();
}

/** 
* 
* Method: stemPorter(String word) 
* 
*/ 
public void testStemPorter() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: stemEnglishStemmer(String word) 
* 
*/ 
public void testStemEnglishStemmer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: stemEnglishAnalyzer(String word) 
* 
*/ 
public void testStemEnglishAnalyzer() throws Exception { 
//TODO: Test goes here... 
} 



public static Test suite() { 
return new TestSuite(StemmerTest.class); 
} 
} 
