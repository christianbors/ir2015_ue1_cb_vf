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

    private Map<String, Newsgroup> documentList;
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

    private Map<String, List<Integer>> textOccurrences = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> xrefOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> referencesOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> pathOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> newsgroupOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> keywordsOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> fromOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> subjectOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> messageidOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> dateOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> organizationOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> replytoOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> distributionOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> followuptoOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> senderOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nntppostinghostOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> articleidOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> returnreceipttoOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nfidOcc = new LinkedHashMap<String, List<Integer>>();
    private Map<String, List<Integer>> nffromOcc = new LinkedHashMap<String, List<Integer>>();

    public BagOfWordsIndex(Map<String, Newsgroup> documents) {
        this.documentList = documents;

        for (Map.Entry<String, Newsgroup> entry : documentList.entrySet()) {
            String[] textTokens = entry.getValue().getTokens().toArray(new String[this.documentList.size()]);
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
        for (Map.Entry<String, Newsgroup> entry : documentList.entrySet()) {
            String[] textTokens = entry.getValue().getTokens().toArray(new String[this.documentList.size()]);
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

            fillOccurrences(this.textDictionary, this.textOccurrences, entry.getKey(), textTokens);
            fillOccurrences(this.xrefDict, this.xrefOcc, entry.getKey(), xrefTokens);
            fillOccurrences(this.referencesDict, this.referencesOcc, entry.getKey(), refTokens);
            fillOccurrences(this.pathDict, this.pathOcc, entry.getKey(), pathTokens);
            fillOccurrences(this.newsgroupDict, this.newsgroupOcc, entry.getKey(), newsgroupTokens);
            fillOccurrences(this.keywordsDict, this.keywordsOcc, entry.getKey(), keywordsTokens);
            fillOccurrences(this.fromDict, this.fromOcc, entry.getKey(), fromToken);
            fillOccurrences(this.subjectDict, this.subjectOcc, entry.getKey(), subjectToken);
            fillOccurrences(this.messageidDict, this.messageidOcc, entry.getKey(), messageidToken);
            fillOccurrences(this.dateDict, this.dateOcc, entry.getKey(), dateToken);
            fillOccurrences(this.organizationDict, this.organizationOcc, entry.getKey(), organizationToken);
            fillOccurrences(this.replytoDict, this.replytoOcc, entry.getKey(), replytoToken);
            fillOccurrences(this.distributionDict, this.distributionOcc, entry.getKey(), distributionToken);
            fillOccurrences(this.followuptoDict, this.followuptoOcc, entry.getKey(), followuptoToken);
            fillOccurrences(this.senderDict, this.senderOcc, entry.getKey(), senderToken);
            fillOccurrences(this.nntppostinghostDict, this.nntppostinghostOcc, entry.getKey(), nntppostinghostToken);
            fillOccurrences(this.articleidDict, this.articleidOcc, entry.getKey(), articleidToken);
            fillOccurrences(this.returnreceipttoDict, this.returnreceipttoOcc, entry.getKey(), returnreceipttoToken);
            fillOccurrences(this.nfidDict, this.nfidOcc, entry.getKey(), nfidToken);
            fillOccurrences(this.nffromDict, this.nffromOcc, entry.getKey(), nffromToken);
        }
//        fillIndex();
        fillXrefIndex();
    }

    private void fillDictionary(Map<String, Integer> dictionary, String[] tokens) {
        for (int i = 0; i < tokens.length; ++i) {
            if (!dictionary.containsKey(tokens[i])) {
                dictionary.put(tokens[i], dictionary.size());
            }
        }
    }

    private void fillOccurrences(Map<String, Integer> dictionary, Map<String, List<Integer>> occurrences, String documentName, String[] tokens) {
        occurrences.put(documentName, new ArrayList(Collections.nCopies(dictionary.size(), 0)));
        int occurrenceIdx = occurrences.size()-1;
        for (int i = 0; i < tokens.length; ++i) {
            List<Integer> currentOccurrence = occurrences.get(occurrenceIdx);
            currentOccurrence.set(dictionary.get(tokens[i]),
                    currentOccurrence.get(dictionary.get(tokens[i])) + 1);
        }
    }

    private void fillXrefIndex() {

    }

    public void writeToJSON(String filename) {
        FileWriter jsonFileWriter = null;
        try {
            jsonFileWriter = new FileWriter(filename + "_bow.json");
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

    public Map<String, Newsgroup> getDocumentList() {
        return documentList;
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

    public Map<String, List<Integer>> getTextOccurrences() {
        return textOccurrences;
    }

    public Map<String, List<Integer>> getXrefOcc() {
        return xrefOcc;
    }

    public Map<String, List<Integer>> getReferencesOcc() {
        return referencesOcc;
    }

    public Map<String, List<Integer>> getPathOcc() {
        return pathOcc;
    }

    public Map<String, List<Integer>> getNewsgroupOcc() {
        return newsgroupOcc;
    }

    public Map<String, List<Integer>> getKeywordsOcc() {
        return keywordsOcc;
    }

    public Map<String, List<Integer>> getFromOcc() {
        return fromOcc;
    }

    public Map<String, List<Integer>> getSubjectOcc() {
        return subjectOcc;
    }

    public Map<String, List<Integer>> getMessageidOcc() {
        return messageidOcc;
    }

    public Map<String, List<Integer>> getDateOcc() {
        return dateOcc;
    }

    public Map<String, List<Integer>> getOrganizationOcc() {
        return organizationOcc;
    }

    public Map<String, List<Integer>> getReplytoOcc() {
        return replytoOcc;
    }

    public Map<String, List<Integer>> getDistributionOcc() {
        return distributionOcc;
    }

    public Map<String, List<Integer>> getFollowuptoOcc() {
        return followuptoOcc;
    }

    public Map<String, List<Integer>> getSenderOcc() {
        return senderOcc;
    }

    public Map<String, List<Integer>> getNntppostinghostOcc() {
        return nntppostinghostOcc;
    }

    public Map<String, List<Integer>> getArticleidOcc() {
        return articleidOcc;
    }

    public Map<String, List<Integer>> getReturnreceipttoOcc() {
        return returnreceipttoOcc;
    }

    public Map<String, List<Integer>> getNfidOcc() {
        return nfidOcc;
    }

    public Map<String, List<Integer>> getNffromOcc() {
        return nffromOcc;
    }
}
