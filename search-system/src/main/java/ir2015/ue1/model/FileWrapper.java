package ir2015.ue1.model;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Velitchko on 4/10/2015.
 */
public class FileWrapper {

    private String name;
    private ArrayList<File> files;

    public FileWrapper(String n, ArrayList<File> f)
    {
        name = n;
        files = f;
    }

    public String getName() { return name; }
    public ArrayList<File> getFiles() { return files; }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < files.size(); i++)
        {
            String str =  files.get(i) + "\n";
            sb.append(str);
        }

        return sb.toString();
    }
}
