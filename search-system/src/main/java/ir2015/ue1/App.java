package ir2015.ue1;

import ir2015.ue1.index.BagOfWordsIndex;
import ir2015.ue1.index.BiGramIndex;
import ir2015.ue1.model.FileWrapper;
import ir2015.ue1.model.FolderLoader;
import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



public class App {


    public static void main(String[] args) {
        boolean caseFold = false;
        boolean removeStopWords = false;
        boolean stemming = false;

        Cli commandLine = new Cli(args);
        commandLine.parse();
        String filename = "";
//<<<<<<< HEAD
//        if (commandLine.isCreateIndex()) {
//            //TODO create index new and store it in a zip-file called indexes.zip
//            System.out.println("Re-creating Index");
//        } else {
//
//            if (commandLine.hasCaseFold()) {
//
//            }
//            if (commandLine.hasRemoveStopwords()) {
//
//            }
//            if (commandLine.hasStemming()) {
////=======
////>>>>>>> f3f8e6fbbef3c13ec9aea5dcc0d4313065294eb8
//
//            }
//            if (commandLine.hasFile()) {
//                filename = commandLine.getTopicFilename();
//            }
//
//            // start search with topic file
//            // The vocabulary parameter determines
//            NewsgroupTopicParser ntp = new NewsgroupTopicParser();
//            //TODO: add Topic-file to search function
//            Newsgroup ng = ntp.parse("../topics/" + filename);
//            ntp.tokenizeText(ng);
//        }

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
        for(int i = 0; i < topic_ngs.size(); i++)
        {
            for(int j = 0; j < newsgroups_list.size(); j++) {
                System.out.println(topic_ngs.get(i));// + " ? " + newsgroups_list.get(j));
                if (topic_ngs.get(i).equals(newsgroups_list.get(j))) {
                    // load them topics
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
                    if(i!=91) {
                        idx = i;
                        doc_term = doc_term_keyset.get(i);
                    }
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

        Map<String, Double> map = new TreeMap<String, Double>(results);
        for(Map.Entry<String, Double> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        return map;

    }

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

    public static void zipIndex(String filename, BiGramIndex bgIdx, String bigramFilename, BagOfWordsIndex bowIdx, String bowFilename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ZipOutputStream zos = new ZipOutputStream(fos);
            bgIdx.writeToJSON(bigramFilename);
            bowIdx.writeToJSON(bowFilename);
            for (String entry : new String[]{bigramFilename, bowFilename}) {
                File file = new File(entry);
                FileInputStream stream = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(entry);
                zos.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = stream.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
                stream.close();
            }
            zos.closeEntry();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readZip(String zipFilename, String outputFoldername) {
        byte[] bytes = new byte[1024];
        try {
            File folder = new File(outputFoldername);
            if (!folder.exists()) {
                folder.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilename));
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String filename = ze.getName();
                File newFile = new File(outputFoldername + File.separator + filename);

                System.out.println("unzip file: " + newFile.getAbsolutePath());

                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(bytes)) > 0) {
                    fos.write(bytes, 0, len);
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
