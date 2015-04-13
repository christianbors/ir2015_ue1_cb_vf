package ir2015.ue1;

import com.google.gson.Gson;
import ir2015.ue1.index.BagOfWordsIndex;
import ir2015.ue1.index.BiGramIndex;
import ir2015.ue1.model.FileWrapper;
import ir2015.ue1.model.FolderLoader;
import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;
import javafx.geometry.Pos;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class App {


    public static void main(String[] args) {
        boolean caseFold = false;
        boolean removeStopWords = false;
        boolean stemming = false;

        Experiments experiments = new Experiments(new HashMap<>());
        String experimentsDoc = "experiments.json";
        File exDocFile = new File(experimentsDoc);
        try {
            if (!exDocFile.exists()) {
                exDocFile.createNewFile();
            } else {
                FileReader experimentsReader = new FileReader(experimentsDoc);
                experiments = new Gson().fromJson(experimentsReader, Experiments.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cli commandLine = new Cli(args);
        commandLine.parse();

        Experiment experiment = new Experiment(commandLine.hasStemming(),
                commandLine.hasCaseFold(),
                commandLine.hasRemoveStopwords(),
                commandLine.isBigram());
        String experimentName = experiments.contains(experiment);
        if (experimentName.isEmpty()) {
            experiments.add(experiment);
        }

        FileWriter jsonFileWriter = null;
        try {
            jsonFileWriter = new FileWriter(experimentsDoc);
            jsonFileWriter.write(new Gson().toJson(experiments));
            jsonFileWriter.flush();
            jsonFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Newsgroup ng = ntp.parse("topics/topic8");
        ntp.tokenizeText(ng);
        //if (!filename.isEmpty()) {
            //TODO: add Topic-file to search function
          //  ng = ntp.parse("topics/topic8");
            //ntp.tokenizeText(ng);
        //}
        //else
        //{
          //  ng = new Newsgroup();
        //}
        // load topic file parse & tokenize
        // find out which newsgroups topic is linked to ( be careful newsgroups might not exist)
        // load ONLY RELEVANT newsgroups BoW/Bi-Gram IDX or construct on the fly
        // Return top 100 results in format <topicid> <Q#> <documentid> <rank> <score> <run-name>
        FolderLoader folders = new FolderLoader("newsgroups/");


        //System.out.println(ng.toString());
       // System.out.println(ng.getNewsgroups().toString());
        ArrayList<String> topic_ngs = ng.getNewsgroups();
        ArrayList<String> newsgroups_list = folders.getNewsgroups();
        ArrayList<FileWrapper> fps = new ArrayList<FileWrapper>();

        for(int i = 0; i < topic_ngs.size(); i++)
        {
            //System.out.println("T: " + topic_ngs.get(i));
            for(int j = 0; j < newsgroups_list.size(); j++) {
                if (topic_ngs.get(i).equals(newsgroups_list.get(j))) {
                    // load them topics
                    fps.add(folders.getFiles(topic_ngs.get(i)));
                    //System.out.println("NG: " + topic_ngs.get(i) + " found in our DB");
                }
            }
        }
        parse_files_list(fps ,ntp, documents);

        //System.out.println("Files loaded & parsed.");
        // Create BoW/Bigram indexes
        Map<String, Newsgroup> query = new HashMap<String, Newsgroup>();
        query.put(filename ,ng);
        BagOfWordsIndex bow_doc = new BagOfWordsIndex(documents);

        BiGramIndex bgi_doc = new BiGramIndex(documents);
        BiGramIndex bgi_query = new BiGramIndex(query);
        //System.out.println(ng.getTokens().toString());
        Map<String, Double> ranking_bow = score(bow_doc, ng.getTokens());
        Map<String, Double> ranking_bgi = score(bgi_doc, bgi_query);
        output(ranking_bgi, filename);
    }

    // Parse all files in Newsgroup directories
    // Put them in the document map to construct the BoW/Bi-Gram indexes later
    public static void parse_files_list(ArrayList<FileWrapper> f, NewsgroupTopicParser ntp, Map<String, Newsgroup> documents)
    {
        //System.out.println("Loading & parsing: " + f.getName());
        for(int i = 0; i < f.size(); i++)
        {
            //System.out.println(f.get(i).getName() + " loaded.");
            FileWrapper fw = f.get(i);
            for (int j = 0; j < fw.getFiles().size(); j++)
            {
                //System.out.println(fw.getName() + "/" + fw.getFiles().get(j).getName());
                Newsgroup ng = ntp.parse(fw.getName() + "/" + fw.getFiles().get(j).getName());
                ntp.tokenizeText(ng);
                documents.put(fw.getName() + "/" + fw.getFiles().get(j).getName(), ng);
            }
        }
    }

    // search document , query
    // Computes the Score of each term in the query to the document
    // Returns a list of results, which should be sorted later
    // Top 100 highest scores to be returned in format:
    // <topicid> <Q#> <documentid> <rank> <score> <run-name>
    public static Map<String, Double> score(BagOfWordsIndex doc, ArrayList<String> query) {
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
        for (Map.Entry<String, List<Integer>> entry : doc_postings.entrySet()) {
            double score = 0.0f;
            file_name = entry.getKey();
            ArrayList<Integer> postings_list = new ArrayList(entry.getValue());

            // get the postings list for each document
            for (int i = 0; i < postings_list.size(); i++) {
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
                for (int j = 0; j < query.size(); j++) {
                    String query_term = query.get(j);

                    // term match
                    if (doc_term.equals(query_term)) {
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

        return results;

    }

    // search document , query
    // Computes the Score of each term in the query to the document
    // Returns a list of results, which should be sorted later
    // Top 100 highest scores to be returned in format:
    // <topicid> <Q#> <documentid> <rank> <score> <run-name>
    public static Map<String, Double> score(BiGramIndex doc, BiGramIndex query)
    {
        double df = 0.0f;
        double tf = 0.0f;
        double idf = 0.0f;
        double tf_idf = 0.0f;
        int num_docs = doc.getDocumentSize();

        Map<String, Double> results = new HashMap<String, Double>();
        String file_name = "";
        // docname, postings list

        // bi-gram , Set<DocumentName, position>
        Map<String, Set<BiGramIndex.Posting>> doc_postings = doc.getTextPostings();
        Map<String ,Set<BiGramIndex.Posting>> query_postings = query.getTextPostings();
        Map<String, Integer> term_count = new HashMap<String, Integer>();

        for (Map.Entry<String, Set<BiGramIndex.Posting>> entry : doc_postings.entrySet()) {
            Set<String> docPerm = new HashSet<String>();
            for(BiGramIndex.Posting posting : entry.getValue()) {
                docPerm.add(posting.getDoc());
            }
            for (String docName : docPerm) {
                if(term_count.containsKey(docName)) {
                    term_count.put(docName, term_count.get(docName) + 1);
                } else {
                    term_count.put(docName, 1);
                }
            }
        }

        for(Map.Entry<String, Set<BiGramIndex.Posting>> entry_d : doc_postings.entrySet())
        {
            double score = 0.0f;

            Set<BiGramIndex.Posting> postings = entry_d.getValue();
            String bi_gram_d = entry_d.getKey();
            int doc_freq = postings.size();

            Set<String> doc_names = new HashSet<String>();
            Map<String, Integer> bigram_term_freq = new HashMap<String, Integer>();

            for (BiGramIndex.Posting posting : postings)
            {

                file_name = posting.getDoc();
                doc_names.add(file_name);

                // query term
                for (Map.Entry<String, Set<BiGramIndex.Posting>> entry_t : query_postings.entrySet())
                {
                    String bi_gram_t = entry_t.getKey();
                    int num_term = 0;
                    if (bi_gram_d.equals(bi_gram_t))
                    {
                        if (bigram_term_freq.containsKey(posting.getDoc())) {
                            bigram_term_freq.put(posting.getDoc(), bigram_term_freq.get(posting.getDoc()) + 1);

                        } else {
                            bigram_term_freq.put(posting.getDoc(), 1);
                        }

                        // compute df, idf, tf, tf_idf
                        df = (double) doc_freq;
                        //System.out.println("DF " + df);
                        idf = getIdf(df, num_docs);
                        //System.out.println("IDF " + idf);
                        tf = (double) bigram_term_freq.get(file_name)/term_count.get(file_name);
                        //System.out.println("TF " + tf);
                        tf_idf = getTfIdf(tf, idf);
                        //System.out.println("TF-IDF " + tf_idf);
                        score += Math.abs(tf_idf);

                    }
                }
            }
            results.put(file_name, score);
        }
        return results;
    }


    // Compute the Document Frequency
    public static double getDocumentFrequency(Integer idx, Map<String, List<Integer>> doc) {
        // returns the number of documents that contain the term
        double df = 0.0f;
        for (Map.Entry<String, List<Integer>> entry : doc.entrySet()) {
            List<Integer> posting = entry.getValue();
            if (posting.get(idx) != 0) {
                df = df + 1;
            }
        }

        return df;
    }

    public static double getDocumentFrequency(String document, Map<String, Set<BiGramIndex.Posting>> postings) {
        double df = 0.0f;
        for (Map.Entry<String, Set<BiGramIndex.Posting>> entry : postings.entrySet()) {
            Set<BiGramIndex.Posting> posting = entry.getValue();
            int count = 0;
            for (BiGramIndex.Posting post : posting) {
                if (post.getDoc().equals(document)) {
                    ++count;
                }
            }
        }
        return df;
    }

    // Compute the Inverse Document Frequency
    public static double getIdf(double df, int num_doc) {
        // simple math stuff
        double idf = 0.0f;
        double x = num_doc / df;
        idf = Math.log(x);

        return idf;
    }

    // Compute the Term Frequency
    public static double getTf(ArrayList<Integer> postings, Integer idx) {
        double num_terms = 0;
        double term_freq = (double) postings.get(idx);
        for (int i = 0; i < postings.size(); i++) {
            if (postings.get(i) != 0) {
                num_terms = num_terms + 1;
            }
        }
        double tf = term_freq / num_terms;
        return tf;
    }

    // Compute TF-IDF for the term, document
    public static double getTfIdf(double tf, double idf) {
        double tf_idf = tf * idf;
        return tf_idf;
    }

    // <topicid> <Q#> <documentid> <rank> <score> <run-name>
    public static void output(Map<String, Double> map, String topic_filename)
    {
        int top_100 = 100;
        int idx = 1;
        Map<String, Double> sorted = sortByValues(map);
        System.out.println("topic" + "\tQ0" + "\t" + "document-id" + "\t" + "rank" + "\t" + "score"  + "" + "\trun-name");
        for(Map.Entry<String, Double> entry : sorted.entrySet())
        {
            if(idx <= 100)
            {
                String file = entry.getKey();
                Double score = entry.getValue();
                //System.out.println("D:" + file + " S: " + score);
                System.out.println(topic_filename + "\tQ0" + "\t" + file + "\t" + idx + "\t" + score  + "" + "\tgroup5-experiment1");
                idx++;
            }
        }
    }

    private static Map<String, Double> sortByValues(Map<String, Double> map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
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
