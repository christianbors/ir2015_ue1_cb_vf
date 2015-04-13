package ir2015.ue1;

import ir2015.ue1.index.BagOfWordsIndex;
import ir2015.ue1.index.BiGramIndex;
import ir2015.ue1.model.Newsgroup;
import ir2015.ue1.parser.NewsgroupTopicParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class App {

    public static void main(String[] args) {
        Cli commandLine = new Cli(args);
        commandLine.parse();
        String filename = "";
        if (commandLine.hasCaseFold()) {

        }
        if (commandLine.hasRemoveStopwords()) {

        }
        if (commandLine.hasStemming()) {

        }
        if (commandLine.hasFile()) {
            filename = commandLine.getTopicFilename();
        }

        // start search with topic file
        // The vocabulary parameter determines
        NewsgroupTopicParser ntp = new NewsgroupTopicParser();
        //TODO: add Topic-file to search function
        Newsgroup ng = ntp.parse("../topics/" + filename);
        ntp.tokenizeText(ng);

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

    public static void readZip(String filename) {
        BiGramIndex
    }
}
