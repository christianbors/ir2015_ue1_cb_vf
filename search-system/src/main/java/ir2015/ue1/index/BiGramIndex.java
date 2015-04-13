package ir2015.ue1.index;

import com.google.gson.Gson;
import ir2015.ue1.model.Newsgroup;

import java.io.*;
import java.util.*;

/**
 * Created by christianbors on 26/03/15.
 */
public class BiGramIndex {

    private Map<String, Set<String>> textDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> xrefDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> referencesDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> pathDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> newsgroupDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> keywordsDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> fromDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> subjectDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> messageidDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> dateDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> organizationDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> replytoDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> distributionDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> followuptoDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> senderDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> nntppostinghostDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> articleidDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> returnreceipttoDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> nfidDict = new TreeMap<String, Set<String>>();
    private Map<String, Set<String>> nffromDict = new TreeMap<String, Set<String>>();

    private Map<String, Set<Posting>> textPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> xrefPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> referencesPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> pathPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> newsgroupPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> keywordsPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> fromPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> subjectPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> messageidPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> datePostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> organizationPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> replytoPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> distributionPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> followuptoPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> senderPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> nntppostinghostPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> articleidPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> returnreceipttoPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> nfidPostings = new TreeMap<String, Set<Posting>>();
    private Map<String, Set<Posting>> nffromPostings = new TreeMap<String, Set<Posting>>();

    public BiGramIndex(Map<String, Newsgroup> documents) {
        for (Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
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

            fillIndexAndPostings(this.textDict, this.textPostings, entry.getKey(), textWords);
            fillIndexAndPostings(this.xrefDict, this.xrefPostings, entry.getKey(), xrefWords);
            fillIndexAndPostings(this.referencesDict, this.referencesPostings, entry.getKey(), refWords);
            fillIndexAndPostings(this.pathDict, this.pathPostings, entry.getKey(), pathWords);
            fillIndexAndPostings(this.newsgroupDict, this.newsgroupPostings, entry.getKey(), newsgroupWords);
            fillIndexAndPostings(this.keywordsDict, this.keywordsPostings, entry.getKey(), keywordsWords);
            fillIndexAndPostings(this.fromDict, this.fromPostings, entry.getKey(), fromWord);
            fillIndexAndPostings(this.subjectDict, this.subjectPostings, entry.getKey(), subjectWord);
            fillIndexAndPostings(this.messageidDict, this.messageidPostings, entry.getKey(), messageidWord);
            fillIndexAndPostings(this.dateDict, this.datePostings, entry.getKey(), dateWord);
            fillIndexAndPostings(this.organizationDict, this.organizationPostings, entry.getKey(), organizationWord);
            fillIndexAndPostings(this.replytoDict, this.replytoPostings, entry.getKey(), replytoWord);
            fillIndexAndPostings(this.distributionDict, this.distributionPostings, entry.getKey(), distributionWord);
            fillIndexAndPostings(this.followuptoDict, this.followuptoPostings, entry.getKey(), followuptoWord);
            fillIndexAndPostings(this.senderDict, this.senderPostings, entry.getKey(), senderWord);
            fillIndexAndPostings(this.nntppostinghostDict, this.nntppostinghostPostings, entry.getKey(), nntppostinghostWord);
            fillIndexAndPostings(this.articleidDict, this.articleidPostings, entry.getKey(), articleidWord);
            fillIndexAndPostings(this.returnreceipttoDict, this.returnreceipttoPostings, entry.getKey(), returnreceipttoWord);
            fillIndexAndPostings(this.nfidDict, this.nfidPostings, entry.getKey(), nfidWord);
            fillIndexAndPostings(this.nffromDict, this.nffromPostings, entry.getKey(), nffromWord);
        }
    }

    private void fillIndexAndPostings(Map<String, Set<String>> index, Map<String, Set<Posting>> postings, String documentName, String[] words) {
        for (int count = 0; count < words.length; ++count) {
            for (String bigram : tokenize(words[count])) {
                Set<Posting> posting;
                if (index.containsKey(bigram)) {
                    // update dictionary
                    index.get(bigram).add(words[count]);
                    // update postings
                    posting = postings.get(bigram);
                } else {
                    // create new dictionary
                    Set<String> wordList = new TreeSet<String>();
                    wordList.add(words[count]);
                    index.put(bigram, wordList);
                    // create new postings
                    posting = new HashSet<Posting>();
                }
                posting.add(new Posting(documentName, count));
                postings.put(bigram, posting);
            }
        }
    }

    private Set<String> tokenize(String word) {
        Set<String> wordset = new TreeSet<String>();
        if (!word.isEmpty()) {
            for (int i = 0; i < word.length(); ++i) {
                if (i == 0) {
                    wordset.add("$" + word.charAt(0));
                    if (word.length() > 1) {
                        wordset.add(word.substring(i, i + 2));
                    }
                } else if (i == word.length() - 1) {
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
            jsonFileWriter = new FileWriter(filename);
            jsonFileWriter.write(new Gson().toJson(this));
            jsonFileWriter.flush();
            jsonFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BiGramIndex readFromJSONFile(String filename) {
        BiGramIndex indexFile = null;
        FileReader jsonFileReader;
        try {
            jsonFileReader = new FileReader(filename + "_bigram.json");
            indexFile = new Gson().fromJson(jsonFileReader, BiGramIndex.class);
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
