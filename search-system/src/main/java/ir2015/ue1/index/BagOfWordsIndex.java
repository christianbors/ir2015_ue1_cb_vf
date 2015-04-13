package ir2015.ue1.index;

import com.google.gson.Gson;
import ir2015.ue1.model.Newsgroup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by christianbors on 26/03/15.
 */
public class BagOfWordsIndex {

    private Map<String, Integer> textDictionary = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> xrefDict = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> referencesDict = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> pathDict = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> newsgroupDict = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> keywordsDict = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> fromDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> subjectDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> messageidDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> dateDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> organizationDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> replytoDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> distributionDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> followuptoDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> senderDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> nntppostinghostDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> articleidDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> returnreceipttoDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> nfidDict= new LinkedHashMap<String, Integer>();
    private Map<String, Integer> nffromDict= new LinkedHashMap<String, Integer>();

    //String stores the filename, List<Integer> stores the occurrences for each word in the document
    private Map<String, List<Integer>> textPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> xrefPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> referencesPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> pathPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> newsgroupPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> keywordsPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> fromPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> subjectPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> messageidPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> datePostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> organizationPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> replytoPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> distributionPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> followuptoPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> senderPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nntppostinghostPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> articleidPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> returnreceipttoPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nfidPostings = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nffromPostings = new LinkedHashMap<String, List<Integer>>();


    public BagOfWordsIndex(Map<String, Newsgroup> documents) {

        for (Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
            String[] textTokens = entry.getValue().getTokens().toArray(new String[documents.size()]);
            String[] xrefTokens = entry.getValue().getXref().toArray(new String[0]);
            String[] refTokens = entry.getValue().getReferences().toArray(new String[0]);
            String[] pathTokens = entry.getValue().getPath().toArray(new String[0]);
            String[] newsgroupTokens = entry.getValue().getNewsgroups().toArray(new String[0]);
            String[] keywordsTokens = entry.getValue().getKeywords().toArray(new String[0]);
            String[] fromToken = {entry.getValue().getFrom()};
            String[] subjectToken = {entry.getValue().getSubject()};
            String[] messageidToken = {entry.getValue().getMessageid()};
            String[] dateToken = {entry.getValue().getDate()};
            String[] organizationToken = {entry.getValue().getOrganization()};
            String[] replytoToken = {entry.getValue().getReplyto()};
            String[] distributionToken = {entry.getValue().getDistribution()};
            String[] followuptoToken = {entry.getValue().getFollowupto()};
            String[] senderToken = {entry.getValue().getSender()};
            String[] nntppostinghostToken = {entry.getValue().getNntppostinghost()};
            String[] articleidToken = {entry.getValue().getArticleid()};
            String[] returnreceipttoToken = {entry.getValue().getReturnreceiptto()};
            String[] nfidToken = {entry.getValue().getNfid()};
            String[] nffromToken = {entry.getValue().getNffrom()};

            fillDictionary(this.textDictionary, textTokens);
            fillDictionary(this.xrefDict, xrefTokens);
            fillDictionary(this.referencesDict, refTokens);
            fillDictionary(this.pathDict, pathTokens);
            fillDictionary(this.newsgroupDict, newsgroupTokens);
            fillDictionary(this.keywordsDict, keywordsTokens);
            fillDictionary(this.fromDict, fromToken);
            fillDictionary(this.subjectDict, subjectToken);
            fillDictionary(this.messageidDict, messageidToken);
            fillDictionary(this.dateDict, dateToken);
            fillDictionary(this.organizationDict, organizationToken);
            fillDictionary(this.replytoDict, replytoToken);
            fillDictionary(this.distributionDict, distributionToken);
            fillDictionary(this.followuptoDict, followuptoToken);
            fillDictionary(this.senderDict, senderToken);
            fillDictionary(this.nntppostinghostDict, nntppostinghostToken);
            fillDictionary(this.articleidDict, articleidToken);
            fillDictionary(this.returnreceipttoDict, returnreceipttoToken);
            fillDictionary(this.nfidDict, nfidToken);
            fillDictionary(this.nffromDict, nffromToken);
        }
        for (Map.Entry<String, Newsgroup> entry : documents.entrySet()) {
            String[] textTokens = entry.getValue().getTokens().toArray(new String[documents.size()]);
            String[] xrefTokens = entry.getValue().getXref().toArray(new String[0]);
            String[] refTokens = entry.getValue().getReferences().toArray(new String[0]);
            String[] pathTokens = entry.getValue().getPath().toArray(new String[0]);
            String[] newsgroupTokens = entry.getValue().getNewsgroups().toArray(new String[0]);
            String[] keywordsTokens = entry.getValue().getKeywords().toArray(new String[0]);
            String[] fromToken = {entry.getValue().getFrom()};
            String[] subjectToken = {entry.getValue().getSubject()};
            String[] messageidToken = {entry.getValue().getMessageid()};
            String[] dateToken = {entry.getValue().getDate()};
            String[] organizationToken = {entry.getValue().getOrganization()};
            String[] replytoToken = {entry.getValue().getReplyto()};
            String[] distributionToken = {entry.getValue().getDistribution()};
            String[] followuptoToken = {entry.getValue().getFollowupto()};
            String[] senderToken = {entry.getValue().getSender()};
            String[] nntppostinghostToken = {entry.getValue().getNntppostinghost()};
            String[] articleidToken = {entry.getValue().getArticleid()};
            String[] returnreceipttoToken = {entry.getValue().getReturnreceiptto()};
            String[] nfidToken = {entry.getValue().getNfid()};
            String[] nffromToken = {entry.getValue().getNffrom()};

            fillPostings(this.textDictionary, this.textPostings, entry.getKey(), textTokens);
            fillPostings(this.xrefDict, this.xrefPostings, entry.getKey(), xrefTokens);
            fillPostings(this.referencesDict, this.referencesPostings, entry.getKey(), refTokens);
            fillPostings(this.pathDict, this.pathPostings, entry.getKey(), pathTokens);
            fillPostings(this.newsgroupDict, this.newsgroupPostings, entry.getKey(), newsgroupTokens);
            fillPostings(this.keywordsDict, this.keywordsPostings, entry.getKey(), keywordsTokens);
            fillPostings(this.fromDict, this.fromPostings, entry.getKey(), fromToken);
            fillPostings(this.subjectDict, this.subjectPostings, entry.getKey(), subjectToken);
            fillPostings(this.messageidDict, this.messageidPostings, entry.getKey(), messageidToken);
            fillPostings(this.dateDict, this.datePostings, entry.getKey(), dateToken);
            fillPostings(this.organizationDict, this.organizationPostings, entry.getKey(), organizationToken);
            fillPostings(this.replytoDict, this.replytoPostings, entry.getKey(), replytoToken);
            fillPostings(this.distributionDict, this.distributionPostings, entry.getKey(), distributionToken);
            fillPostings(this.followuptoDict, this.followuptoPostings, entry.getKey(), followuptoToken);
            fillPostings(this.senderDict, this.senderPostings, entry.getKey(), senderToken);
            fillPostings(this.nntppostinghostDict, this.nntppostinghostPostings, entry.getKey(), nntppostinghostToken);
            fillPostings(this.articleidDict, this.articleidPostings, entry.getKey(), articleidToken);
            fillPostings(this.returnreceipttoDict, this.returnreceipttoPostings, entry.getKey(), returnreceipttoToken);
            fillPostings(this.nfidDict, this.nfidPostings, entry.getKey(), nfidToken);
            fillPostings(this.nffromDict, this.nffromPostings, entry.getKey(), nffromToken);
        }
    }

    private void fillDictionary(Map<String, Integer> dictionary, String[] tokens) {
        for (int i = 0; i < tokens.length; ++i) {
            if (tokens[i] != null) {
                if (!dictionary.containsKey(tokens[i])) {
                    dictionary.put(tokens[i], dictionary.size());
                }
            }
        }
    }

    private void fillPostings(Map<String, Integer> dictionary, Map<String, List<Integer>> postings, String documentName, String[] tokens) {
        postings.put(documentName, new ArrayList(Collections.nCopies(dictionary.size(), 0)));
        int occurrenceIdx = postings.size()-1;
        for (int i = 0; i < tokens.length; ++i) {
            if (tokens[i] != null) {
                List<Integer> currentOccurrence = postings.get(documentName);
                currentOccurrence.set(dictionary.get(tokens[i]),
                        currentOccurrence.get(dictionary.get(tokens[i])) + 1);
            }
        }
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

    public BagOfWordsIndex readFromJSONFile(String filename) {
        BagOfWordsIndex indexFile = null;
        FileReader jsonFileReader;
        try {
            jsonFileReader = new FileReader(filename + "_bow.json");
            indexFile = new Gson().fromJson(jsonFileReader, BagOfWordsIndex.class);
            jsonFileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexFile;
    }

    public Map<String, Integer> getTextDictionary() {
        return textDictionary;
    }

    public Map<String, Integer> getXrefDict() {
        return xrefDict;
    }

    public Map<String, Integer> getReferencesDict() {
        return referencesDict;
    }

    public Map<String, Integer> getPathDict() {
        return pathDict;
    }

    public Map<String, Integer> getNewsgroupDict() {
        return newsgroupDict;
    }

    public Map<String, Integer> getKeywordsDict() {
        return keywordsDict;
    }

    public Map<String, Integer> getFromDict() {
        return fromDict;
    }

    public Map<String, Integer> getSubjectDict() {
        return subjectDict;
    }

    public Map<String, Integer> getMessageidDict() {
        return messageidDict;
    }

    public Map<String, Integer> getDateDict() {
        return dateDict;
    }

    public Map<String, Integer> getOrganizationDict() {
        return organizationDict;
    }

    public Map<String, Integer> getReplytoDict() {
        return replytoDict;
    }

    public Map<String, Integer> getDistributionDict() {
        return distributionDict;
    }

    public Map<String, Integer> getFollowuptoDict() {
        return followuptoDict;
    }

    public Map<String, Integer> getSenderDict() {
        return senderDict;
    }

    public Map<String, Integer> getNntppostinghostDict() {
        return nntppostinghostDict;
    }

    public Map<String, Integer> getArticleidDict() {
        return articleidDict;
    }

    public Map<String, Integer> getReturnreceipttoDict() {
        return returnreceipttoDict;
    }

    public Map<String, Integer> getNfidDict() {
        return nfidDict;
    }

    public Map<String, Integer> getNffromDict() {
        return nffromDict;
    }

    public Map<String, List<Integer>> getTextPostings() {
        return textPostings;
    }

    public Map<String, List<Integer>> getXrefPostings() {
        return xrefPostings;
    }

    public Map<String, List<Integer>> getReferencesPostings() {
        return referencesPostings;
    }

    public Map<String, List<Integer>> getPathPostings() {
        return pathPostings;
    }

    public Map<String, List<Integer>> getNewsgroupPostings() {
        return newsgroupPostings;
    }

    public Map<String, List<Integer>> getKeywordsPostings() {
        return keywordsPostings;
    }

    public Map<String, List<Integer>> getFromPostings() {
        return fromPostings;
    }

    public Map<String, List<Integer>> getSubjectPostings() {
        return subjectPostings;
    }

    public Map<String, List<Integer>> getMessageidPostings() {
        return messageidPostings;
    }

    public Map<String, List<Integer>> getDatePostings() {
        return datePostings;
    }

    public Map<String, List<Integer>> getOrganizationPostings() {
        return organizationPostings;
    }

    public Map<String, List<Integer>> getReplytoPostings() {
        return replytoPostings;
    }

    public Map<String, List<Integer>> getDistributionPostings() {
        return distributionPostings;
    }

    public Map<String, List<Integer>> getFollowuptoPostings() {
        return followuptoPostings;
    }

    public Map<String, List<Integer>> getSenderPostings() {
        return senderPostings;
    }

    public Map<String, List<Integer>> getNntppostinghostPostings() {
        return nntppostinghostPostings;
    }

    public Map<String, List<Integer>> getArticleidPostings() {
        return articleidPostings;
    }

    public Map<String, List<Integer>> getReturnreceipttoPostings() {
        return returnreceipttoPostings;
    }

    public Map<String, List<Integer>> getNfidPostings() {
        return nfidPostings;
    }

    public Map<String, List<Integer>> getNffromPostings() {
        return nffromPostings;
    }
}
