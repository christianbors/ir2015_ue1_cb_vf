package ir2015.ue1;

/**
 * Created by christianbors on 21/03/15.
 */
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.*;

public class Cli {
    private static final Logger log = Logger.getLogger(Cli.class.getName());
    private String[] args = null;
    private Options options = new Options();

    private boolean hasCaseFold = false;
    private boolean hasRemoveStopwords = false;
    private boolean hasStemming = false;

    private String filename = "";

    private final String caseFold = "case-fold";
    private final String removeStopwords = "remove-stopwords";
    private final String stemming = "stemming";

    public Cli(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help.");

        /*// Newsgroup indexing
        Option indexScoring = OptionBuilder.withLongOpt("index-bag-of-words")
                .withDescription("Index Scoring Methods: bag of words (bow), bi-gram (bg)")
                .create("bow");
        Option indexBiGram = OptionBuilder.withLongOpt("index-bi-gram")
                .withDescription("Create an index with the bi-gram algorithm")
                .create("bg");
        options.addOption(indexBagOfWords);
        options.addOption(indexBiGram);

        // Vocabulary normalization
        Option vocabulary = OptionBuilder.withArgName("technique")
                .hasArg()
                .withLongOpt("normalize")
                .withDescription("Normalize vocabulary by applying a combination of " +
                        "\"case-fold\", \"remove-stopwords\", \"stemming\"")
                .create("n");
        Option file      = OptionBuilder.withArgName("filename")
                .hasArg()
                .withDescription("input file for search system")
                .withLongOpt("file")
                .create("f");
        options.addOption(vocabulary);
        options.addOption(file);
*/
        // Search
        Option searchParameter = OptionBuilder.withArgName("search")
                .hasArg()
                .withLongOpt("search")
                .withDescription("Search String: ")
                .create("S");
        options.addOption(searchParameter);
        Option scoringMethod = OptionBuilder.withArgName("vocabulary")
                .hasArgs()
                .withLongOpt("vocabulary")
                .withDescription("Scoring methods: \"" + caseFold + "\", \"" + removeStopwords + "\", \"" + stemming + "\"")
                .create("v");
        Option filename = OptionBuilder.withArgName("file")
                .hasArg()
                .withLongOpt("filename")
                .withDescription("Filename to provide a topic to search for")
                .create("f");
        scoringMethod.setRequired(false);
        filename.setRequired(false);
        searchParameter.setRequired(true);
        options.addOption(scoringMethod);
        options.addOption(filename);
    }

    public CommandLine parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (!cmd.hasOption("S")) {
                throw new ParseException("missing Search parameter!");
            }

            if (cmd.hasOption("f")) {
                filename = cmd.getOptionValue("f");
            }

            if (cmd.hasOption("v")) {
                // Whatever you want to do with the setting goes here
                log.log(Level.INFO, "Using cli argument -v= " + cmd.getOptionValue("v"));
                StringTokenizer tokenizer = new StringTokenizer(cmd.getOptionValue("v"), ",");
                while(tokenizer.hasMoreTokens()) {
                    String option = tokenizer.nextToken();
                    if(option.equals(caseFold)) {
                        hasCaseFold = true;
                    } else if (option.equals(removeStopwords)) {
                        hasRemoveStopwords = true;
                    } else if (option.equals(stemming)) {
                        hasStemming = true;
                    } else {
                        throw new ParseException("unrecognized -v argument: " + option);
                    }
                }
            } else {
//                log.log(Level.SEVERE, "Missing v option");
//                help();
                log.log(Level.INFO, "no vocabulary option submitted");
            }
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse command line properties", e);
            help();
        }
        return cmd;
    }

    private void help() {
        // This prints out some help
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("Search System", options);
        System.exit(0);
    }

    public boolean hasCaseFold() {
        return hasCaseFold;
    }

    public boolean hasRemoveStopwords() {
        return hasRemoveStopwords;
    }

    public boolean hasStemming() {
        return hasStemming;
    }

    public boolean hasFile() {
        return !filename.isEmpty();
    }

    public String getFilename() {
        return filename;
    }
}

