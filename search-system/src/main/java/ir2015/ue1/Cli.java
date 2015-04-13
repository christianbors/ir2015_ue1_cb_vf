package ir2015.ue1;

/**
 * Created by christianbors on 21/03/15.
 */

import java.io.File;
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

    private boolean createIndex = false;

    private boolean bigram = false;
    private boolean bow = false;

    private String topicsPath = "../topics/";
    private String topicFilename = "";

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
        Option file      = OptionBuilder.withArgName("topicFilename")
                .hasArg()
                .withDescription("input file for search system")
                .withLongOpt("file")
                .create("f");
        options.addOption(vocabulary);
        options.addOption(file);
*/
        // Search
        Option scoringMethod = OptionBuilder.withArgName("vocabulary")
                .hasArgs()
                .withLongOpt("vocabulary")
                .withDescription("Vocabulary processing methods: \"" + caseFold + "\", \"" + removeStopwords + "\", \"" + stemming + "\"")
                .create("v");
        Option filename = OptionBuilder.withArgName("file")
                .hasArg()
                .withLongOpt("topicFilename")
                .withDescription("Filename to provide a topic to search for")
                .create("f");
        Option indexingMethod = OptionBuilder.withArgName("index")
                .hasArg()
                .withLongOpt("index")
                .withDescription("Available Indexing methods: bigram (Bi-Gram), bow (Bag-of-Words)")
                .create("i");
        Option createIndex = OptionBuilder.withArgName("createIndex")
                .hasArg()
                .withLongOpt("create-index")
                .withDescription("Re-generate the index. Options: bigram (Bi-Gram), bow (Bag-of-Words)")
                .create();
        scoringMethod.setRequired(false);
        indexingMethod.setRequired(true);
        options.addOption(scoringMethod);
        options.addOption(filename);
        options.addOption(indexingMethod);
        options.addOption(createIndex);
    }

    public CommandLine parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("create-index")) {
                createIndex = true;
            }
            if (cmd.hasOption("i")) {
                if (cmd.getOptionValue("i").equals("bigram")) {
                    bigram = true;
                } else if (cmd.getOptionValue("i").equals("bow")) {
                    bow = true;
                } else {
                    throw new ParseException("No matching indexing method found");
                }
            } else {
                throw new ParseException("No index method provided");
            }
            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("f")) {
                topicFilename = cmd.getOptionValue("f");
                File file = new File(topicsPath + topicFilename);
                if (!file.exists()) {
                    System.out.println(file.getAbsolutePath());
                    System.out.println("File not found: " + topicFilename);
                    System.exit(0);
                }
            }

            if (cmd.hasOption("v")) {
                // Whatever you want to do with the setting goes here
                log.log(Level.INFO, "Using cli argument -v= " + cmd.getOptionValue("v"));
                StringTokenizer tokenizer = new StringTokenizer(cmd.getOptionValue("v"), ",");
                while (tokenizer.hasMoreTokens()) {
                    String option = tokenizer.nextToken();
                    if (option.equals(caseFold)) {
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
//                log.log(Level.WARNING, "no vocabulary option submitted");
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
        return !topicFilename.isEmpty();
    }

    public boolean isCreateIndex() {
        return createIndex;
    }

    public boolean isBigram() {
        return bigram;
    }

    public boolean isBow() {
        return bow;
    }

    public String getTopicFilename() {
        return topicFilename;
    }
}

