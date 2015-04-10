package ir2015.ue1;

import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;

public class App {

    public static void main(String[] args) {
        Cli commandLine = new Cli(args);
        commandLine.parse();
        String filename = "";
        if (commandLine.hasCaseFold()) {

        }
        if (commandLine.hasRemoveStopwords()) {

        }
        if (commandLine.hasStemming()) {

        }
        if (commandLine.hasFile()) {
            filename = commandLine.getTopicFilename();
        }

        // start search with topic file
        // The vocabulary parameter determines
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();
        //TODO: add Topic-file to search function
        Newsgroup ng = ntp.parse("../topics/" + filename);
        ntp.tokenizeText(ng);

    }

}
