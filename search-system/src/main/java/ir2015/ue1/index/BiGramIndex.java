package ir2015.ue1.index;

import com.google.gson.Gson;
import ir2015.ue1.model.Newsgroup;

import java.io.*;
import java.util.*;

/**
 * Created by christianbors on 26/03/15.
 */
public class BiGramIndex {

    private int documentSize;
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

    public Map<String, Set<String>> getTextDict() {
        return textDict;
    }

    public void setTextDict(Map<String, Set<String>> textDict) {
        this.textDict = textDict;
    }

    public Map<String, Set<String>> getXrefDict() {
        return xrefDict;
    }

    public void setXrefDict(Map<String, Set<String>> xrefDict) {
        this.xrefDict = xrefDict;
    }

    public Map<String, Set<String>> getReferencesDict() {
        return referencesDict;
    }

    public void setReferencesDict(Map<String, Set<String>> referencesDict) {
        this.referencesDict = referencesDict;
    }

    public Map<String, Set<String>> getPathDict() {
        return pathDict;
    }

    public void setPathDict(Map<String, Set<String>> pathDict) {
        this.pathDict = pathDict;
    }

    public Map<String, Set<String>> getNewsgroupDict() {
        return newsgroupDict;
    }

    public void setNewsgroupDict(Map<String, Set<String>> newsgroupDict) {
        this.newsgroupDict = newsgroupDict;
    }

    public Map<String, Set<String>> getKeywordsDict() {
        return keywordsDict;
    }

    public void setKeywordsDict(Map<String, Set<String>> keywordsDict) {
        this.keywordsDict = keywordsDict;
    }

    public Map<String, Set<String>> getFromDict() {
        return fromDict;
    }

    public void setFromDict(Map<String, Set<String>> fromDict) {
        this.fromDict = fromDict;
    }

    public Map<String, Set<String>> getSubjectDict() {
        return subjectDict;
    }

    public void setSubjectDict(Map<String, Set<String>> subjectDict) {
        this.subjectDict = subjectDict;
    }

    public Map<String, Set<String>> getMessageidDict() {
        return messageidDict;
    }

    public void setMessageidDict(Map<String, Set<String>> messageidDict) {
        this.messageidDict = messageidDict;
    }

    public Map<String, Set<String>> getDateDict() {
        return dateDict;
    }

    public void setDateDict(Map<String, Set<String>> dateDict) {
        this.dateDict = dateDict;
    }

    public Map<String, Set<String>> getOrganizationDict() {
        return organizationDict;
    }

    public void setOrganizationDict(Map<String, Set<String>> organizationDict) {
        this.organizationDict = organizationDict;
    }

    public Map<String, Set<String>> getReplytoDict() {
        return replytoDict;
    }

    public void setReplytoDict(Map<String, Set<String>> replytoDict) {
        this.replytoDict = replytoDict;
    }

    public Map<String, Set<String>> getDistributionDict() {
        return distributionDict;
    }

    public void setDistributionDict(Map<String, Set<String>> distributionDict) {
        this.distributionDict = distributionDict;
    }

    public Map<String, Set<String>> getFollowuptoDict() {
        return followuptoDict;
    }

    public void setFollowuptoDict(Map<String, Set<String>> followuptoDict) {
        this.followuptoDict = followuptoDict;
    }

    public Map<String, Set<String>> getSenderDict() {
        return senderDict;
    }

    public void setSenderDict(Map<String, Set<String>> senderDict) {
        this.senderDict = senderDict;
    }

    public Map<String, Set<String>> getNntppostinghostDict() {
        return nntppostinghostDict;
    }

    public void setNntppostinghostDict(Map<String, Set<String>> nntppostinghostDict) {
        this.nntppostinghostDict = nntppostinghostDict;
    }

    public Map<String, Set<String>> getArticleidDict() {
        return articleidDict;
    }

    public void setArticleidDict(Map<String, Set<String>> articleidDict) {
        this.articleidDict = articleidDict;
    }

    public Map<String, Set<String>> getReturnreceipttoDict() {
        return returnreceipttoDict;
    }

    public void setReturnreceipttoDict(Map<String, Set<String>> returnreceipttoDict) {
        this.returnreceipttoDict = returnreceipttoDict;
    }

    public Map<String, Set<String>> getNfidDict() {
        return nfidDict;
    }

    public void setNfidDict(Map<String, Set<String>> nfidDict) {
        this.nfidDict = nfidDict;
    }

    public Map<String, Set<String>> getNffromDict() {
        return nffromDict;
    }

    public void setNffromDict(Map<String, Set<String>> nffromDict) {
        this.nffromDict = nffromDict;
    }

    public Map<String, Set<Posting>> getTextPostings() {
        return textPostings;
    }

    public void setTextPostings(Map<String, Set<Posting>> textPostings) {
        this.textPostings = textPostings;
    }

    public Map<String, Set<Posting>> getXrefPostings() {
        return xrefPostings;
    }

    public void setXrefPostings(Map<String, Set<Posting>> xrefPostings) {
        this.xrefPostings = xrefPostings;
    }

    public Map<String, Set<Posting>> getReferencesPostings() {
        return referencesPostings;
    }

    public void setReferencesPostings(Map<String, Set<Posting>> referencesPostings) {
        this.referencesPostings = referencesPostings;
    }

    public Map<String, Set<Posting>> getPathPostings() {
        return pathPostings;
    }

    public void setPathPostings(Map<String, Set<Posting>> pathPostings) {
        this.pathPostings = pathPostings;
    }

    public Map<String, Set<Posting>> getNewsgroupPostings() {
        return newsgroupPostings;
    }

    public void setNewsgroupPostings(Map<String, Set<Posting>> newsgroupPostings) {
        this.newsgroupPostings = newsgroupPostings;
    }

    public Map<String, Set<Posting>> getKeywordsPostings() {
        return keywordsPostings;
    }

    public void setKeywordsPostings(Map<String, Set<Posting>> keywordsPostings) {
        this.keywordsPostings = keywordsPostings;
    }

    public Map<String, Set<Posting>> getFromPostings() {
        return fromPostings;
    }

    public void setFromPostings(Map<String, Set<Posting>> fromPostings) {
        this.fromPostings = fromPostings;
    }

    public Map<String, Set<Posting>> getSubjectPostings() {
        return subjectPostings;
    }

    public void setSubjectPostings(Map<String, Set<Posting>> subjectPostings) {
        this.subjectPostings = subjectPostings;
    }

    public Map<String, Set<Posting>> getMessageidPostings() {
        return messageidPostings;
    }

    public void setMessageidPostings(Map<String, Set<Posting>> messageidPostings) {
        this.messageidPostings = messageidPostings;
    }

    public Map<String, Set<Posting>> getDatePostings() {
        return datePostings;
    }

    public void setDatePostings(Map<String, Set<Posting>> datePostings) {
        this.datePostings = datePostings;
    }

    public Map<String, Set<Posting>> getOrganizationPostings() {
        return organizationPostings;
    }

    public void setOrganizationPostings(Map<String, Set<Posting>> organizationPostings) {
        this.organizationPostings = organizationPostings;
    }

    public Map<String, Set<Posting>> getReplytoPostings() {
        return replytoPostings;
    }

    public void setReplytoPostings(Map<String, Set<Posting>> replytoPostings) {
        this.replytoPostings = replytoPostings;
    }

    public Map<String, Set<Posting>> getDistributionPostings() {
        return distributionPostings;
    }

    public void setDistributionPostings(Map<String, Set<Posting>> distributionPostings) {
        this.distributionPostings = distributionPostings;
    }

    public Map<String, Set<Posting>> getFollowuptoPostings() {
        return followuptoPostings;
    }

    public void setFollowuptoPostings(Map<String, Set<Posting>> followuptoPostings) {
        this.followuptoPostings = followuptoPostings;
    }

    public Map<String, Set<Posting>> getSenderPostings() {
        return senderPostings;
    }

    public void setSenderPostings(Map<String, Set<Posting>> senderPostings) {
        this.senderPostings = senderPostings;
    }

    public Map<String, Set<Posting>> getNntppostinghostPostings() {
        return nntppostinghostPostings;
    }

    public void setNntppostinghostPostings(Map<String, Set<Posting>> nntppostinghostPostings) {
        this.nntppostinghostPostings = nntppostinghostPostings;
    }

    public Map<String, Set<Posting>> getArticleidPostings() {
        return articleidPostings;
    }

    public void setArticleidPostings(Map<String, Set<Posting>> articleidPostings) {
        this.articleidPostings = articleidPostings;
    }

    public Map<String, Set<Posting>> getReturnreceipttoPostings() {
        return returnreceipttoPostings;
    }

    public void setReturnreceipttoPostings(Map<String, Set<Posting>> returnreceipttoPostings) {
        this.returnreceipttoPostings = returnreceipttoPostings;
    }

    public Map<String, Set<Posting>> getNfidPostings() {
        return nfidPostings;
    }

    public void setNfidPostings(Map<String, Set<Posting>> nfidPostings) {
        this.nfidPostings = nfidPostings;
    }

    public Map<String, Set<Posting>> getNffromPostings() {
        return nffromPostings;
    }

    public void setNffromPostings(Map<String, Set<Posting>> nffromPostings) {
        this.nffromPostings = nffromPostings;
    }

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
        this.documentSize = documents.size();
        for (Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
            Newsgroup ng = entry.getValue();
            //TODO: change split
            String[] textWords = ng.getTokens().toArray(new String[0]);
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

    public int getDocumentSize()
    {
        return this.documentSize;
    }

    private void fillIndexAndPostings(Map<String, Set<String>> index, Map<String, Set<Posting>> postings, String documentName, String[] words) {
        for (int count = 0; count < words.length; ++count) {
            for (String bigram : tokenize(words[count])) {
                if (bigram != null) {
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

    public class Posting {
        final String doc;
        final int pos;


        Posting(String document, int position) {
            this.doc = document;
            this.pos = position;
        }

        public String getDoc()
        {
            return this.doc;
        }

        public int getPos()
        {
            return this.pos;
        }
    }
}
