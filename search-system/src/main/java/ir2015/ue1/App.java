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
            filename = commandLine.getFilename();
        }
        // The vocabulary parameter determines
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();

        // start search with topic file
        if (!filename.isEmpty()) {
            //TODO: add Topic-file to search function
            Newsgroup ng = ntp.parse("topics/" + filename);
            ntp.tokenizeText(ng);
        }
    }

}
