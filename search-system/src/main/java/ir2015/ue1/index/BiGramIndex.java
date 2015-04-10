package ir2015.ue1.index;

import com.google.gson.Gson;
import ir2015.ue1.model.Newsgroup;

import java.io.*;
import java.util.*;

/**
 * Created by christianbors on 26/03/15.
 */
public class BiGramIndex {

    private Map<String, Set<String>> textDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> xrefDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> referencesDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> pathDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> newsgroupDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> keywordsDict = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> fromDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> subjectDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> messageidDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> dateDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> organizationDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> replytoDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> distributionDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> followuptoDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> senderDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> nntppostinghostDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> articleidDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> returnreceipttoDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> nfidDict= new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> nffromDict= new LinkedHashMap<String, Set<String>>();

    private Map<String, List<Posting>> textPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> xrefPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> referencesPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> pathPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> newsgroupPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> keywordsPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> fromPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> subjectPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> messageidPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> datePostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> organizationPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> replytoPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> distributionPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> followuptoPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> senderPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> nntppostinghostPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> articleidPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> returnreceipttoPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> nfidPostings = new LinkedHashMap<String, List<Posting>>();
    private Map<String, List<Posting>> nffromPostings = new LinkedHashMap<String, List<Posting>>();

    public BiGramIndex(Map<String, Newsgroup> documents) {
        for(Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
            Newsgroup ng = entry.getValue();
            //TODO: change split
            String[] textWords = ng.getText().split("\\s");
            String[] xrefWords = ng.getXref().toArray(new String[0]);
            String[] refWords = ng.getReferences().toArray(new String[0]);
            String[] pathWords = ng.getPath().toArray(new String[0]);
            String[] newsgroupWords = ng.getNewsgroups().toArray(new String[0]);
            String[] keywordsWords = ng.getKeywords().toArray(new String[0]);
            String[] fromWord = {ng.getFrom()};
            String[] subjectWord = {ng.getSubject()};
            String[] messageidWord = {ng.getMessageid()};
            String[] dateWord = {ng.getDate()};
            String[] organizationWord = {ng.getOrganization()};
            String[] replytoWord = {ng.getReplyto()};
            String[] distributionWord = {ng.getDistribution()};
            String[] followuptoWord = {ng.getFollowupto()};
            String[] senderWord = {ng.getSender()};
            String[] nntppostinghostWord = {ng.getNntppostinghost()};
            String[] articleidWord = {ng.getArticleid()};
            String[] returnreceipttoWord = {ng.getReturnreceiptto()};
            String[] nfidWord = {ng.getNfid()};
            String[] nffromWord = {ng.getNffrom()};

            fillIndex(this.textDict, textWords);
            fillIndex(this.xrefDict, xrefWords);
            fillIndex(this.referencesDict, refWords);
            fillIndex(this.pathDict, pathWords);
            fillIndex(this.newsgroupDict, newsgroupWords);
            fillIndex(this.keywordsDict, keywordsWords);
            fillIndex(this.fromDict, fromWord);
            fillIndex(this.subjectDict, subjectWord);
            fillIndex(this.messageidDict, messageidWord);
            fillIndex(this.dateDict, dateWord);
            fillIndex(this.organizationDict, organizationWord);
            fillIndex(this.replytoDict, replytoWord);
            fillIndex(this.distributionDict, distributionWord);
            fillIndex(this.followuptoDict, followuptoWord);
            fillIndex(this.senderDict, senderWord);
            fillIndex(this.nntppostinghostDict, nntppostinghostWord);
            fillIndex(this.articleidDict, articleidWord);
            fillIndex(this.returnreceipttoDict, returnreceipttoWord);
            fillIndex(this.nfidDict, nfidWord);
            fillIndex(this.nffromDict, nffromWord);
        }
        for(Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
            Newsgroup ng = entry.getValue();
            //TODO: change split
            String[] textWords = ng.getText().split("\\s");
            String[] xrefWords = ng.getXref().toArray(new String[0]);
            String[] refWords = ng.getReferences().toArray(new String[0]);
            String[] pathWords = ng.getPath().toArray(new String[0]);
            String[] newsgroupWords = ng.getNewsgroups().toArray(new String[0]);
            String[] keywordsWords = ng.getKeywords().toArray(new String[0]);
            String[] fromWord = {ng.getFrom()};
            String[] subjectWord = {ng.getSubject()};
            String[] messageidWord = {ng.getMessageid()};
            String[] dateWord = {ng.getDate()};
            String[] organizationWord = {ng.getOrganization()};
            String[] replytoWord = {ng.getReplyto()};
            String[] distributionWord = {ng.getDistribution()};
            String[] followuptoWord = {ng.getFollowupto()};
            String[] senderWord = {ng.getSender()};
            String[] nntppostinghostWord = {ng.getNntppostinghost()};
            String[] articleidWord = {ng.getArticleid()};
            String[] returnreceipttoWord = {ng.getReturnreceiptto()};
            String[] nfidWord = {ng.getNfid()};
            String[] nffromWord = {ng.getNffrom()};

            fillPostings(this.textDict, this.textPostings, entry.getKey(), textWords);
            fillPostings(this.xrefDict, this.xrefPostings, entry.getKey(), xrefWords);
            fillPostings(this.referencesDict, this.referencesPostings, entry.getKey(), refWords);
            fillPostings(this.pathDict, this.pathPostings, entry.getKey(), pathWords);
            fillPostings(this.newsgroupDict, this.newsgroupPostings, entry.getKey(), newsgroupWords);
            fillPostings(this.keywordsDict, this.keywordsPostings, entry.getKey(), keywordsWords);
            fillPostings(this.fromDict, this.fromPostings, entry.getKey(), fromWord);
            fillPostings(this.subjectDict, this.subjectPostings, entry.getKey(), subjectWord);
            fillPostings(this.messageidDict, this.messageidPostings, entry.getKey(), messageidWord);
            fillPostings(this.dateDict, this.datePostings, entry.getKey(), dateWord);
            fillPostings(this.organizationDict, this.organizationPostings, entry.getKey(), organizationWord);
            fillPostings(this.replytoDict, this.replytoPostings, entry.getKey(), replytoWord);
            fillPostings(this.distributionDict, this.distributionPostings, entry.getKey(), distributionWord);
            fillPostings(this.followuptoDict, this.followuptoPostings, entry.getKey(), followuptoWord);
            fillPostings(this.senderDict, this.senderPostings, entry.getKey(), senderWord);
            fillPostings(this.nntppostinghostDict, this.nntppostinghostPostings, entry.getKey(), nntppostinghostWord);
            fillPostings(this.articleidDict, this.articleidPostings, entry.getKey(), articleidWord);
            fillPostings(this.returnreceipttoDict, this.returnreceipttoPostings, entry.getKey(), returnreceipttoWord);
            fillPostings(this.nfidDict, this.nfidPostings, entry.getKey(), nfidWord);
            fillPostings(this.nffromDict, this.nffromPostings, entry.getKey(), nffromWord);
        }
    }

    private void fillIndex(Map<String, Set<String>> index, String[] words) {
        for (String word : words) {
            for (String bigram : tokenize(word)) {
                if (index.containsKey(bigram)) {
                    index.get(bigram).add(word);
                } else {
                    Set<String> wordList = new HashSet<String>();
                    wordList.add(word);
                    index.put(bigram, wordList);
                }
            }
        }
    }

    private void fillPostings(Map<String, Set<String>> index, Map<String, List<Posting>> postings, String documentName, String[] words) {
        for (String key : index.keySet()) {
            postings.put(key, new LinkedList<Posting>());
        }
        for (int count = 0; count < words.length; ++count) {
            for (String bigram : tokenize(words[count])) {
                postings.get(bigram).add(new Posting(documentName, count));
            }
        }
    }

    private Set<String> tokenize(String word) {
        Set<String> wordset = new HashSet<String>();
        if (!word.isEmpty()) {
            for (int i = 0; i < word.length(); ++i) {
                if (i == 0) {
                    wordset.add("$" + word.charAt(0));
                    if (word.length() > 1) {
                        wordset.add(word.substring(i, i + 2));
                    }
                } else if (i == word.length()-1) {
                    wordset.add(word.charAt(i) + "$");
                } else {
                    if (word.length() > 1) {
                        wordset.add(word.substring(i, i + 2));
                    }
                }
            }
        }
        return wordset;
    }

    public void writeToJSON(String filename) {
        FileWriter jsonFileWriter = null;
        try {
            jsonFileWriter = new FileWriter(filename + "_bigram.json");
            jsonFileWriter.write(new Gson().toJson(this));
            jsonFileWriter.flush();
            jsonFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BagOfWordsIndex readFromJSONFile(String filename) {
        BagOfWordsIndex indexFile = null;
        FileReader jsonFileReader;
        try {
            jsonFileReader = new FileReader(filename + "_bigram.json");
            indexFile = new Gson().fromJson(jsonFileReader, BagOfWordsIndex.class);
            jsonFileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexFile;
    }

    class Posting {
        final String doc;
        final int pos;

        Posting(String document, int position) {
            this.doc = document;
            this.pos = position;
        }
    }
}
