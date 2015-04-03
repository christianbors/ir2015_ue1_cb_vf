package ir2015.ue1;

import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import org.apache.commons.cli.*;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Options;

public class App {

     public static void main(String[] args) {
         //CommandLine commandLine = new Cli(args).parse();

         // The vocabulary parameter determines
         NewsgroupTopicParser ntp = new NewsgroupTopicParser();
         Newsgroup ng = ntp.parse("topics/topic1");
         ntp.tokenizeText(ng);
     }

}
