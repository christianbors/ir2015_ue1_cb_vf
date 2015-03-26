package ir2015.ue1.parser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by christianbors on 22/03/15.
 */
public class NewsgroupTopicParser {

    public void foo() {

    }

    public String typeDetect() {
        // parse String before colon
        //    -> zb. From: livesey@solntze.wpd.sgi.com (Jon Livesey)

        //        "From"
        BufferedReader reader = new BufferedReader("test.txt");

        try {
            String line = reader.readLine();
            // find text up to the first ":" symbol and write into attribute
            String attribute = "";
            switch (attribute) {
                case "From":
            }
                //write into FROM variable
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
