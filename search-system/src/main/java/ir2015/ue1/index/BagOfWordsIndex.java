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

    private List<Newsgroup> documentList;
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

    private List<List<Integer>> textOccurrences = new LinkedList<List<Integer>>();
    private List<List<Integer>> xrefOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> referencesOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> pathOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> newsgroupOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> keywordsOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> fromOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> subjectOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> messageidOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> dateOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> organizationOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> replytoOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> distributionOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> followuptoOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> senderOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> nntppostinghostOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> articleidOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> returnreceipttoOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> nfidOcc = new LinkedList<List<Integer>>();
    private List<List<Integer>> nffromOcc = new LinkedList<List<Integer>>();

    public BagOfWordsIndex(List<Newsgroup> documents) {
        this.documentList = documents;

        for (int docCount = 0; docCount < this.documentList.size(); ++docCount) {
            String[] textTokens = this.documentList.get(docCount).getText().split("\\s");
            String[] xrefTokens = this.documentList.get(docCount).getXref().toArray(new String[0]);
            String[] refTokens = this.documentList.get(docCount).getReferences().toArray(new String[0]);
            String[] pathTokens = this.documentList.get(docCount).getPath().toArray(new String[0]);
            String[] newsgroupTokens = this.documentList.get(docCount).getNewsgroups().toArray(new String[0]);
            String[] keywordsTokens = this.documentList.get(docCount).getKeywords().toArray(new String[0]);
            String[] fromToken = {this.documentList.get(docCount).getFrom()};
            String[] subjectToken = {this.documentList.get(docCount).getSubject()};
            String[] messageidToken = {this.documentList.get(docCount).getMessageid()};
            String[] dateToken = {this.documentList.get(docCount).getDate()};
            String[] organizationToken = {this.documentList.get(docCount).getOrganization()};
            String[] replytoToken = {this.documentList.get(docCount).getReplyto()};
            String[] distributionToken = {this.documentList.get(docCount).getDistribution()};
            String[] followuptoToken = {this.documentList.get(docCount).getFollowupto()};
            String[] senderToken = {this.documentList.get(docCount).getSender()};
            String[] nntppostinghostToken = {this.documentList.get(docCount).getNntppostinghost()};
            String[] articleidToken = {this.documentList.get(docCount).getArticleid()};
            String[] returnreceipttoToken = {this.documentList.get(docCount).getReturnreceiptto()};
            String[] nfidToken = {this.documentList.get(docCount).getNfid()};
            String[] nffromToken = {this.documentList.get(docCount).getNffrom()};

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
        for (int docCount = 0; docCount < this.documentList.size(); ++docCount) {
            String[] textTokens = this.documentList.get(docCount).getText().split("\\s");
            String[] xrefTokens = this.documentList.get(docCount).getXref().toArray(new String[0]);
            String[] refTokens = this.documentList.get(docCount).getReferences().toArray(new String[0]);
            String[] pathTokens = this.documentList.get(docCount).getPath().toArray(new String[0]);
            String[] newsgroupTokens = this.documentList.get(docCount).getNewsgroups().toArray(new String[0]);
            String[] keywordsTokens = this.documentList.get(docCount).getKeywords().toArray(new String[0]);
            String[] fromToken = {this.documentList.get(docCount).getFrom()};
            String[] subjectToken = {this.documentList.get(docCount).getSubject()};
            String[] messageidToken = {this.documentList.get(docCount).getMessageid()};
            String[] dateToken = {this.documentList.get(docCount).getDate()};
            String[] organizationToken = {this.documentList.get(docCount).getOrganization()};
            String[] replytoToken = {this.documentList.get(docCount).getReplyto()};
            String[] distributionToken = {this.documentList.get(docCount).getDistribution()};
            String[] followuptoToken = {this.documentList.get(docCount).getFollowupto()};
            String[] senderToken = {this.documentList.get(docCount).getSender()};
            String[] nntppostinghostToken = {this.documentList.get(docCount).getNntppostinghost()};
            String[] articleidToken = {this.documentList.get(docCount).getArticleid()};
            String[] returnreceipttoToken = {this.documentList.get(docCount).getReturnreceiptto()};
            String[] nfidToken = {this.documentList.get(docCount).getNfid()};
            String[] nffromToken = {this.documentList.get(docCount).getNffrom()};

            fillOccurrences(this.textDictionary, this.textOccurrences, textTokens);
            fillOccurrences(this.xrefDict, this.xrefOcc, xrefTokens);
            fillOccurrences(this.referencesDict, this.referencesOcc, refTokens);
            fillOccurrences(this.pathDict, this.pathOcc, pathTokens);
            fillOccurrences(this.newsgroupDict, this.newsgroupOcc, newsgroupTokens);
            fillOccurrences(this.keywordsDict, this.keywordsOcc, keywordsTokens);
            fillOccurrences(this.fromDict, this.fromOcc, fromToken);
            fillOccurrences(this.subjectDict, this.subjectOcc, subjectToken);
            fillOccurrences(this.messageidDict, this.messageidOcc, messageidToken);
            fillOccurrences(this.dateDict, this.dateOcc, dateToken);
            fillOccurrences(this.organizationDict, this.organizationOcc, organizationToken);
            fillOccurrences(this.replytoDict, this.replytoOcc, replytoToken);
            fillOccurrences(this.distributionDict, this.distributionOcc, distributionToken);
            fillOccurrences(this.followuptoDict, this.followuptoOcc, followuptoToken);
            fillOccurrences(this.senderDict, this.senderOcc, senderToken);
            fillOccurrences(this.nntppostinghostDict, this.nntppostinghostOcc, nntppostinghostToken);
            fillOccurrences(this.articleidDict, this.articleidOcc, articleidToken);
            fillOccurrences(this.returnreceipttoDict, this.returnreceipttoOcc, returnreceipttoToken);
            fillOccurrences(this.nfidDict, this.nfidOcc, nfidToken);
            fillOccurrences(this.nffromDict, this.nffromOcc, nffromToken);
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

    private void fillOccurrences(Map<String, Integer> dictionary, List<List<Integer>> occurrences, String[] tokens) {
        occurrences.add(
                new ArrayList(
                        Collections.nCopies(dictionary.size(), 0)));
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
            jsonFileWriter = new FileWriter(filename + ".json");
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
            jsonFileReader = new FileReader(filename + ".json");
            indexFile = new Gson().fromJson(jsonFileReader, BagOfWordsIndex.class);
            jsonFileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexFile;
    }

    public List<Newsgroup> getDocumentList() {
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

    public List<List<Integer>> getTextOccurrences() {
        return textOccurrences;
    }

    public List<List<Integer>> getXrefOcc() {
        return xrefOcc;
    }

    public List<List<Integer>> getReferencesOcc() {
        return referencesOcc;
    }

    public List<List<Integer>> getPathOcc() {
        return pathOcc;
    }

    public List<List<Integer>> getNewsgroupOcc() {
        return newsgroupOcc;
    }

    public List<List<Integer>> getKeywordsOcc() {
        return keywordsOcc;
    }

    public List<List<Integer>> getFromOcc() {
        return fromOcc;
    }

    public List<List<Integer>> getSubjectOcc() {
        return subjectOcc;
    }

    public List<List<Integer>> getMessageidOcc() {
        return messageidOcc;
    }

    public List<List<Integer>> getDateOcc() {
        return dateOcc;
    }

    public List<List<Integer>> getOrganizationOcc() {
        return organizationOcc;
    }

    public List<List<Integer>> getReplytoOcc() {
        return replytoOcc;
    }

    public List<List<Integer>> getDistributionOcc() {
        return distributionOcc;
    }

    public List<List<Integer>> getFollowuptoOcc() {
        return followuptoOcc;
    }

    public List<List<Integer>> getSenderOcc() {
        return senderOcc;
    }

    public List<List<Integer>> getNntppostinghostOcc() {
        return nntppostinghostOcc;
    }

    public List<List<Integer>> getArticleidOcc() {
        return articleidOcc;
    }

    public List<List<Integer>> getReturnreceipttoOcc() {
        return returnreceipttoOcc;
    }

    public List<List<Integer>> getNfidOcc() {
        return nfidOcc;
    }

    public List<List<Integer>> getNffromOcc() {
        return nffromOcc;
    }
}
