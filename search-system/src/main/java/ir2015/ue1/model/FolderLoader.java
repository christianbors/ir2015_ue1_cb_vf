package ir2015.ue1.model;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Velitchko on 4/10/2015.
 */

public class FolderLoader {
    private String root;
    private ArrayList<File> newsgroup;
    public FolderLoader(String str)
    {
        root = str;
        newsgroup = new ArrayList<File>();
        File root_dir = new File(root);
        File[] folders = root_dir.listFiles();

        for(int i = 0; i < folders.length; i++)
        {
            if(folders[i].isDirectory())
            {
                newsgroup.add(folders[i]);

            }

               //System.out.println(newsgroup.get(i).getName());
        }
    }

    public FileWrapper getFiles(String ng)
    {
        ArrayList<File> res = new ArrayList<File>();
        FileWrapper fp = null;
        File f = new File(root+ "/" + ng);
        File[] files = f.listFiles();

        for(int i = 0; i < files.length; i++)
        {
            res.add(files[i]);

            //System.out.println(f.getName() + "/" + res.get(i).getName());
            //System.out.println(root + f.getName());
        }

        return new FileWrapper(root + f.getName(), res);
    }
}
