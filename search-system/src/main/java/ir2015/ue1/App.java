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

        //Cli commandLine = new Cli(args);
        //commandLine.parse();
        String filename = "";

        Map<String, Newsgroup> documents = new LinkedHashMap<String, Newsgroup>();

        // if (commandLine.hasCaseFold()) {
        caseFold = true;
        //}
        //if (commandLine.hasRemoveStopwords()) {
        removeStopWords = true;
        //}
        //if (commandLine.hasStemming()) {
        stemming = true;
        //}
        //if (commandLine.hasFile()) {
        //filename = commandLine.getFilename();
        //}
        NewsgroupTopicParser ntp = new NewsgroupTopicParser(caseFold, removeStopWords, stemming);
        // The vocabulary parameter determine

        // start search with topic file
        if (!filename.isEmpty()) {
            //TODO: add Topic-file to search function
            Newsgroup ng = ntp.parse("topics/" + filename);
            ntp.tokenizeText(ng);
        }
        // load topic file parse & tokenize
        // find out which newsgroups topic is linked to ( be careful newsgroups might not exist)
        // load ONLY RELEVANT newsgroups BoW/Bi-Gram IDX or construct on the fly
        // Return top 100 results in format <topicid> <Q#> <documentid> <rank> <score> <run-name>
        FolderLoader folders = new FolderLoader("newsgroups/");
        FileWrapper atheism = folders.getFiles("alt.atheism");
        Newsgroup ng = ntp.parse("topics/topic7");
        ntp.tokenizeText(ng);
        //System.out.println(ng.toString());
        ArrayList<String> topic_ngs = ng.getNewsgroups();
        ArrayList<String> newsgroups_list = folders.getNewsgroups();
        ArrayList<FileWrapper> fps = new ArrayList<FileWrapper>();

        for(int i = 0; i < topic_ngs.size(); i++)
        {
            for(int j = 0; j < newsgroups_list.size(); j++) {
                System.out.println(topic_ngs.get(i));// + " ? " + newsgroups_list.get(j));
                if (topic_ngs.get(i).equals(newsgroups_list.get(j))) {
                    // load them topics
                    fps.add(folders.getFiles(topic_ngs.get(i)));
                 //   System.out.println("NG: " + topic_ngs.get(i) + " found in our DB");
                }
            }
        }

        // Load all newsgroups + parse + tokenize
        // Whole result is in ArrayList<Newsgroup> documents


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






        //System.out.println(bow_topic.getTextDictionary());
        //System.out.println(bow_topic.getTextOccurrences().toString());

        score(bow_doc, ng.getTokens());
        System.out.println("Done.");
    }

    // Parse all files in Newsgroup directories
    // Put them in the document map to construct the BoW/Bi-Gram indexes later
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
    // Computes the Score of each term in the query to the document
    // Returns a list of results, which should be sorted later
    // Top 100 highest scores to be returned in format:
    // <topicid> <Q#> <documentid> <rank> <score> <run-name>
    public static Map<String, Double> score(BagOfWordsIndex doc, ArrayList<String> query)
    {
        double df = 0.0f;
        double tf = 0.0f;
        double idf = 0.0f;
        double tf_idf = 0.0f;


        Map<String, Double> results = new HashMap<String, Double>();

        // docname, postings list
        Map<String, List<Integer>> doc_postings = doc.getTextPostings();
        // doc term, freq
        Map<String, Integer> doc_terms = doc.getTextDictionary();
        // list of doc terms
        ArrayList<String> doc_term_keyset = new ArrayList(doc_terms.keySet());
        String file_name = "";
        // go through all documents in set
        for(Map.Entry<String, List<Integer>> entry : doc_postings.entrySet())
        {
            double score = 0.0f;
            file_name = entry.getKey();
            ArrayList<Integer> postings_list = new ArrayList(entry.getValue());

            // get the postings list for each document
            for(int i = 0; i < postings_list.size(); i++)
            {
                String doc_term = "";

                // if postings list @ index i != 0
                // the term occurs in the document so we get that term
                int idx = 0;
                if(postings_list.get(i) != 0)
                {
                        idx = i;
                        doc_term = doc_term_keyset.get(i);
                }
                // try to find out which string is associated with each non-zero entry in the postings list
                // and compare that to the list of terms from the query
                // if entry @ postings list for this doc is not null
                // the string is present in the documents terms
                // get that doc term and start comparing to the query terms
                // save the index of this term
                // Only Throws NPE @ Index 91
                // For some reason in postings file @ index 91 there is nothing
                // @ BOW Construction
                // go through each term in query
                // start comparing once we have a match
                // compute TF / DF / IDF / TF-IDF of the match
                // add to score
                for(int j = 0; j < query.size(); j++)
                {
                    String query_term = query.get(j);

                    // term match
                    if(doc_term.equals(query_term))
                    {
                        //System.out.println("TERM MATCH");
                        //System.out.println("DOC: " + file_name + " D:" + doc_term + " T:" + query_term);

                        // send document idx + postings file to compute DF
                        df = getDocumentFrequency(idx, doc_postings);
                        //System.out.println("DF: " + df);
                        // send DF + number of documents to compute I
                        idf = getIdf(df, doc_postings.size());
                        //System.out.println("IDF: " + idf);
                        // send postings list + idx of term we are checking
                        tf = getTf(postings_list, idx);
                        //System.out.println("TF: " + tf);
                        // compute tf_idf as tf * idf
                        tf_idf = getTfIdf(tf, idf);
                        //System.out.println("TF-IDF: " + tf_idf);
                        // add up score
                        score += tf_idf;
                    }
                }
            }
            results.put(file_name, score);
            //System.out.print(".");
        }
        // 400 RESULTS
        // TODO:  all 0.0 scored?
        // Print out sorted by name (can sort by score later)

        //Map<String, Double> map = new TreeMap<String, Double>(results);
        //for(Map.Entry<String, Double> entry : map.entrySet())
       // {
         //   System.out.println(entry.getKey() + " : " + entry.getValue());
        //}
        return results;

    }

    // OLD WRONG CODE
    // search document , query
    // Computes the Score of each term in the query to the document
    // Returns a list of results, which should be sorted later
    // Top 100 highest scores to be returned in format:
    // <topicid> <Q#> <documentid> <rank> <score> <run-name>
//    public static void search_terms(BagOfWordsIndex bow_d, BagOfWordsIndex bow_t)
//    {
//        // stuff
//        double tf; // term frequency
//        double df; // document frequency
//        double idf; // inverse document frequency
//        double tf_idf; // term frequency inverse document frequency
//        double score = 0.0f; // query score
//
//        // Save results here
//        // FILENAME, SCORE
//        Map<String, Double> results = new LinkedHashMap<String, Double>();
//
//        // dictionary / terms
//        Map<String, Integer> d_terms = bow_d.getTextDictionary();
//        Map<String, Integer> term_terms = bow_t.getTextDictionary();
//
//        // postings / frequency
//        Map<String, List<Integer>> term_freq = bow_t.getTextPostings();
//        Map<String, List<Integer>> d_freq = bow_d.getTextPostings();
//
//        // term count per doc
//        Map<String, Integer> ttf = bow_d.getTerm_freq();
//        //System.out.println(d_freq);
//        // go through all terms in query
//        int idx = 0;
//        System.out.println("Q term " + term_terms.size());
//        System.out.println("D term " + d_terms.values());
//        System.out.println("T freq " + term_freq.size());
//        System.out.println("D freq " + d_freq.size());
//
//        for(Map.Entry<String, Integer> entry_t : term_terms.entrySet())
//        {
//            // get terms
//            String t_term = entry_t.getKey();
//            String file_name = "";
//            // go through all terms in document
//
//            for(Map.Entry<String, Integer> entry_d : d_terms.entrySet())
//            {
//                String d_term = entry_d.getKey();
//                //System.out.println("t " + t_term + " d " + d_term );
//                // check if equal
//                if(t_term.equals(d_term)) {
//                    // we got a term match between query and document
//                    // get index of document term
//                    // check in occurences the frequency
//                    //System.out.println("Match: " + t_term + " @ " + entry_d.getValue());
//
//                    // number of documents that contain the term we found a match for
//                    df = getDocumentFrequency(entry_d.getValue(), d_freq);
//                    // inverse document frequency
//                    idf = getIdf(df, d_freq.size());
//                    // term frequency
//                    tf = getTf(d_freq, entry_d.getValue(), ttf);
//                    // compute tfidf = tf * idf
//                    tf_idf = getTfIdf(tf, idf);
//                    score += tf_idf;
//                }
//                // score here
//                // score(query, document) = sum ( tf_idf(term, doc) ) over all terms in query
//            }
//            // How to resolve filename?
//            //System.out.println(score);
//            // get file name?
//            List<String> keys = new ArrayList(d_freq.keySet());
//            for(int i = 0; i < keys.size(); i++)
//            {
//                if(i == idx) {
//                    file_name = keys.get(i);
//                    break;
//                }
//            }
//            results.put(file_name, score);
//            idx++;
//            score = 0.0f;
//        }
//        //System.out.println(results.toString());
//        System.out.println(results.size());
//    }

    // Compute the Document Frequency
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

    // Compute the Inverse Document Frequency
    public static double getIdf(double df, int num_doc)
    {
        // simple math stuff
        double idf = 0.0f;
        double x = num_doc / df;
        idf = Math.log(x);

        return idf;
    }

    // OLD WRONG CODE
    // Compute the Term Frequency
//    public static double getTf(Map<String, List<Integer>> doc, Integer idx)
//    {
//        double tf = 0.0f;
//        double num_terms = 0.0f;
//        for(Map.Entry<String, List<Integer>> entry : doc.entrySet())
//        {
//            String file_name = entry.getKey();
//
//            //get total number of terms in doc from ttf
//            num_terms = ttf.get(file_name);
//            List<Integer> posting = entry.getValue();
//            if(posting.get(idx) != 0)
//            {
//                tf = tf + 1;
//            }
//        }
//        if(num_terms == 0)
//        {
//            System.out.println("DIV BY 0");
//        }
//        try {
//            tf = tf / num_terms;
//        }
//        catch(ArithmeticException e)
//        {
//            // probably div by 0 => NaN result
//        }
//
//        return tf;
//    }

    // Compute the Term Frequency
    public static double getTf(ArrayList<Integer> postings, Integer idx)
    {
        double num_terms = 0;
        double term_freq = (double) postings.get(idx);
        for(int i = 0; i < postings.size(); i++)
        {
            if(postings.get(i) != 0) {
                num_terms = num_terms + 1;
            }
        }
        double tf = term_freq / num_terms;
        return tf;
    }
    // Compute TF-IDF for the term, document
    public static double getTfIdf(double tf, double idf)
    {
        double tf_idf = tf * idf;
        return tf_idf;
    }

    public static void output(Map<String, Double> map)
    {
        int top_100 = 100;


    }
}
