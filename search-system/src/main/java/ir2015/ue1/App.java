package ir2015.ue1;

import ir2015.ue1.index.BagOfWordsIndex;
import ir2015.ue1.model.FileWrapper;
import ir2015.ue1.model.FolderLoader;
import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.Options;



public class App {


    public static void main(String[] args) {
        boolean caseFold = false;
        boolean removeStopWords = false;
        boolean stemming = false;

        Cli commandLine = new Cli(args);
        commandLine.parse();
        String filename = "";

        Map<String, Newsgroup> documents = new LinkedHashMap<String, Newsgroup>();

        if (commandLine.hasCaseFold()) {
            caseFold = true;
        }
        if (commandLine.hasRemoveStopwords()) {
            removeStopWords = true;
        }
        if (commandLine.hasStemming()) {
            stemming = true;
        }
        if (commandLine.hasFile()) {
            filename = commandLine.getFilename();
        }
        NewsgroupTopicParser ntp = new NewsgroupTopicParser(caseFold, removeStopWords, stemming);
        // The vocabulary parameter determine

        // start search with topic file
        if (!filename.isEmpty()) {
            //TODO: add Topic-file to search function
            Newsgroup ng = ntp.parse("topics/" + filename);
            ntp.tokenizeText(ng);
        }

        // Load all newsgroups + parse + tokenize
        // Whole result is in ArrayList<Newsgroup> documents
        FolderLoader folders = new FolderLoader("newsgroups/");
        FileWrapper atheism = folders.getFiles("alt.atheism");
//        FileWrapper graphics = folders.getFiles("comp.graphics");
//        FileWrapper windows = folders.getFiles("comp.os.ms-windows.misc");
//        FileWrapper ibm_hardware = folders.getFiles("comp.sys.ibm.pc.hardware");
//        FileWrapper mac_hardware = folders.getFiles("comp.sys.mac.hardware");
//        FileWrapper windows_x = folders.getFiles("comp.windows.x");
//        FileWrapper forsale = folders.getFiles("misc.forsale");
//        FileWrapper autos = folders.getFiles("rec.autos");
//        FileWrapper motorcycles = folders.getFiles("rec.motorcycles");
//        FileWrapper baseball = folders.getFiles("rec.sport.baseball");
//        FileWrapper hockey = folders.getFiles("rec.sport.hockey");
//        FileWrapper crypt = folders.getFiles("sci.crypt");
//        FileWrapper electronics = folders.getFiles("sci.electronics");
//        FileWrapper med = folders.getFiles("sci.med");
//        FileWrapper space = folders.getFiles("sci.space");
//        FileWrapper christian = folders.getFiles("soc.religion.christian");
//        FileWrapper guns = folders.getFiles("talk.politics.guns");
//        FileWrapper mideast = folders.getFiles("talk.politics.mideast");
//        FileWrapper misc = folders.getFiles("talk.politics.misc");
//        FileWrapper religion_misc = folders.getFiles("talk.religion.misc");
//        //System.out.println(atheism.toString());
        // Parse all files
        parse_files_list(atheism ,ntp, documents);
//        parse_files_list(graphics ,ntp, documents);
//        parse_files_list(windows ,ntp, documents);
//        parse_files_list(ibm_hardware ,ntp, documents);
//        parse_files_list(mac_hardware ,ntp, documents);
//        parse_files_list(windows_x ,ntp, documents);
//        parse_files_list(forsale ,ntp, documents);
//        parse_files_list(autos ,ntp, documents);
//        parse_files_list(motorcycles ,ntp, documents);
//        parse_files_list(baseball ,ntp, documents);
//        parse_files_list(hockey ,ntp, documents);
//        parse_files_list(crypt ,ntp, documents);
//        parse_files_list(electronics ,ntp, documents);
//        parse_files_list(med ,ntp, documents);
//        parse_files_list(space ,ntp, documents);
//        parse_files_list(christian ,ntp, documents);
//        parse_files_list(guns ,ntp, documents);
//        parse_files_list(mideast ,ntp, documents);
//        parse_files_list(misc ,ntp, documents);
//        parse_files_list(religion_misc ,ntp, documents);

        System.out.println("Files loaded & parsed.");
        // Create BoW/Bigram indexes
        BagOfWordsIndex bow_doc = new BagOfWordsIndex(documents);
        //System.out.println(bow.getTextDictionary().toString());
        //System.out.println(bow.getTextOccurrences().toString());

        Map<String, Newsgroup> topics = new LinkedHashMap<String, Newsgroup>();
        Newsgroup ng = ntp.parse("topics/topic8");
        ntp.tokenizeText(ng);
        topics.put("topics/topic8", ng);

        BagOfWordsIndex bow_topic = new BagOfWordsIndex(topics);
        //System.out.println(bow_topic.getTextDictionary());
        //System.out.println(bow_topic.getTextOccurrences().toString());

        search_terms(bow_doc, bow_topic);
    }

    public static void parse_files_list(FileWrapper f, NewsgroupTopicParser ntp, Map<String, Newsgroup> documents)
    {
        System.out.println("Loading & parsing: " + f.getName());
        for(int i = 0; i < f.getFiles().size(); i++)
        {
            //System.out.println(f.getName() + "/" + f.getFiles().get(i).getName());
            Newsgroup ng = ntp.parse(f.getName() + "/" + f.getFiles().get(i).getName());
            ntp.tokenizeText(ng);
            documents.put(f.getName() + "/" + f.getFiles().get(i).getName(),ng);
        }
    }

    // search document , query
    public static void search_terms(BagOfWordsIndex bow_d, BagOfWordsIndex bow_t)
    {
        // stuff
        double tf; // term frequency
        double df; // document frequency
        double idf; // inverse document frequency
        double tf_idf; // term frequency inverse document frequency
        double score = 0.0f; // query score

        // Save results here
        // FILENAME, SCORE
        Map<String, Double> results = new LinkedHashMap<String, Double>();

        // dictionary / terms
        Map<String, Integer> d_terms = bow_d.getTextDictionary();
        Map<String, Integer> term_terms = bow_t.getTextDictionary();

        // postings / frequency
        Map<String, List<Integer>> term_freq = bow_t.getTextPostings();
        Map<String, List<Integer>> d_freq = bow_d.getTextPostings();

        // term count per doc
        Map<String, Integer> ttf = bow_d.getTerm_freq();
        //System.out.println(d_freq);
        // go through all terms in query
        for(Map.Entry<String, Integer> entry_t : term_terms.entrySet())
        {
            // get terms
            String t_term = entry_t.getKey();
            String file_name = "";
            // go through all terms in document
            for(Map.Entry<String, Integer> entry_d : d_terms.entrySet())
            {
                String d_term = entry_d.getKey();
                //System.out.println("t " + t_term + " d " + d_term );
                // check if equal
                if(t_term.equals(d_term)) {
                    // we got a term match between query and document
                    // get index of document term
                    // check in occurences the frequency
                    //System.out.println("Match: " + t_term + " @ " + entry_d.getValue());

                    // number of documents that contain the term we found a match for
                    df = getDocumentFrequency(entry_d.getValue(), d_freq);
                    // inverse document frequency
                    idf = getIdf(df, d_freq.size());
                    // term frequency
                    tf = getTf(d_freq, entry_d.getValue(), ttf);
                    // compute tfidf = tf * idf
                    tf_idf = getTfIdf(tf, idf);
                    score += tf_idf;
                }
                // score here
                // score(query, document) = sum ( tf_idf(term, doc) ) over all terms in query




            }
            // How to resolve filename?
            results.put(file_name, score);
            score = 0.0f;

        }


        System.out.println(results.toString());
    }

    public static double getDocumentFrequency(Integer idx, Map<String, List<Integer>> doc)
    {
        // returns the number of documents that contain the term
        double df = 0.0f;
        for(Map.Entry<String, List<Integer>> entry : doc.entrySet())
        {
            List<Integer> posting = entry.getValue();
            if(posting.get(idx) != 0)
            {
                df = df + 1;
            }
        }

        return df;
    }

    public static double getIdf(double df, int num_doc)
    {
        // simple math stuff
        double idf = 0.0f;
        double x = num_doc / df;
        idf = Math.log(x);

        return idf;
    }

    public static double getTf(Map<String, List<Integer>> doc, Integer idx, Map<String, Integer> ttf)
    {
        double tf = 0.0f;
        double num_terms = 0.0f;
        for(Map.Entry<String, List<Integer>> entry : doc.entrySet())
        {
            String file_name = entry.getKey();

            //get total number of terms in doc from ttf
            num_terms = ttf.get(file_name);
            List<Integer> posting = entry.getValue();
            if(posting.get(idx) != 0)
            {
                tf = tf + 1;
            }
        }
        if(num_terms == 0)
        {
            System.out.println("DIV BY 0");
        }
        tf = tf / num_terms;

        return tf;
    }

    public static double getTfIdf(double tf, double idf)
    {
        return tf * idf;
    }
}
