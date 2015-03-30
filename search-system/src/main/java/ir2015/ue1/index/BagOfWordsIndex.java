package ir2015.ue1.index;

import com.google.gson.Gson;
import ir2015.ue1.model.Newsgroup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by christianbors on 26/03/15.
 */
public class BagOfWordsIndex {
    private List<Newsgroup> documentList;
    private Map<String, Integer> textDictionary;
    private Map<String, Integer> xrefDict;
    private Map<String, Integer> referencesDict;
    private Map<String, Integer> pathDict;
    private Map<String, Integer> newsgroupDict;
    private Map<String, Integer> keywordsDict;
    private List<List<Integer>> textOccurrences;
    private List<List<Integer>> xrefOcc;
    private List<List<Integer>> referencesOcc;
    private List<List<Integer>> pathOcc;
    private List<List<Integer>> newsgroupOcc;
    private List<List<Integer>> keywordsOcc;

    public BagOfWordsIndex(List<Newsgroup> documents) {
        this.documentList = documents;
        //text
        this.textDictionary = new LinkedHashMap<String, Integer>();
        this.textOccurrences = new LinkedList<List<Integer>>();
        //xref
        this.xrefDict = new LinkedHashMap<String, Integer>();
        this.xrefOcc = new LinkedList<List<Integer>>();
        for (int docCount = 0; docCount < this.documentList.size(); ++docCount) {
            // text index
            String[] textTokens = this.documentList.get(docCount).getText().split("\\s");
            this.textDictionary = fillDictionary(this.textDictionary, textTokens);
            //xref index
            String[] xrefTokens = this.documentList.get(docCount).getXref().toArray(new String[0]);
            this.xrefDict = fillDictionary(this.xrefDict, xrefTokens);
        }
        for (int docCount = 0; docCount < this.documentList.size(); ++docCount) {
            // text index
            String[] textTokens = this.documentList.get(docCount).getText().split("\\s");
            fillOccurrences(this.textDictionary, this.textOccurrences, textTokens);
            //xref index
            String[] xrefTokens = this.documentList.get(docCount).getXref().toArray(new String[0]);
            fillOccurrences(this.xrefDict, xrefOcc, xrefTokens);
        }
//        fillIndex();
        fillXrefIndex();
    }

    private Map<String, Integer> fillDictionary(Map<String, Integer> dictionary, String[] tokens) {
        for (int i = 0; i < tokens.length; ++i) {
            if (!dictionary.containsKey(tokens[i])) {
                dictionary.put(tokens[i], dictionary.size());
            }
        }
        System.out.println(dictionary.size());
        return dictionary;
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
            jsonFileWriter = new FileWriter(filename + "_dictionary.json");
            jsonFileWriter.write(new Gson().toJson(this.getTextDictionary()));
            jsonFileWriter.flush();
            jsonFileWriter.close();

            jsonFileWriter = new FileWriter(filename + "_occurrences.json");
            jsonFileWriter.write(new Gson().toJson(this.getTextOccurrences()));
            jsonFileWriter.flush();
            jsonFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getTextDictionary() {
        return textDictionary;
    }

    public List<List<Integer>> getTextOccurrences() {
        return textOccurrences;
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
}
