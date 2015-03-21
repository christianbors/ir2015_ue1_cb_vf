package ir2015.ue1;

import org.apache.commons.cli.Options;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Options opt = new Options();

        opt.addOption("t", false, "display current time");
        System.out.println( "Hello World!" );
    }
}
