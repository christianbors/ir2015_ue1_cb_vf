package ir2015.ue1.parser;

import ir2015.ue1.model.Newsgroup;
import org.tartarus.snowball.ext.PorterStemmer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



/**
 * Created by christianbors on 22/03/15.
 */
public class NewsgroupTopicParser {

    private static Stopwords stopwords;
    private PorterStemmer stemmer;
    private boolean caseFold;
    private boolean removeStopwords;
    private boolean stemming;

    // constructor
    public NewsgroupTopicParser(boolean caseFold, boolean removeStopwords, boolean stemming)
    {
        this.caseFold = caseFold;
        this.removeStopwords = removeStopwords;
        this.stemming = stemming;

        if(stemming)
        {
            stemmer = new PorterStemmer();
        }
        if(removeStopwords)
        {
            stopwords = Stopwords.getInstance();
        }
    }

    // Parses through meta data and content
    // Creates a Newsgroup item and sets all relevant information
    public Newsgroup parse(String file_name)
    {
        Newsgroup ng = new Newsgroup();
        BufferedReader reader;
        StringBuilder text_content = new StringBuilder();
        String line = "";
        boolean empty_line = false;

        try
        {
            reader = new BufferedReader(new FileReader(file_name));

            while ((line = reader.readLine()) != null)
            {
                String attribute = "";
                String value = "";

                if(!empty_line)
                {
                    try
                    {
                        attribute = line.substring(0, line.indexOf(":"));
                        value = line.substring(line.indexOf(":"), line.length());

                    }
                    catch(StringIndexOutOfBoundsException e)
                    {

                    }
                }
                // First empty line indicates that message content is started
                // We are done with parsing metadata
                if(line.isEmpty() && !empty_line)
                {
                    empty_line = true;
                }

                // switch statement doenst work with strings anymore.
                if (attribute.equals(Attributes.PATH.toString()) && !empty_line)
                {
                    String[] values = value.split("!");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setPath(values[i]);
                    }
                    //delimiter !
                }
                else if (attribute.equals(Attributes.FROM.toString()) && !empty_line)
                {
                    ng.setFrom(value);
                }
                else if (attribute.equals(Attributes.NEWSGROUPS.toString()) && !empty_line)
                {
                    String[] values = value.split(",");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setNewsgroups(values[i]);
                    }
                    // delimiter ,
                }
                else if (attribute.equals(Attributes.SUBJECT.toString()) && !empty_line)
                {
                    ng.setSubject(value);
                }
                else if (attribute.equals(Attributes.MESSAGEID.toString()) && !empty_line)
                {
                    ng.setMessageid(value);
                }
                else if (attribute.equals(Attributes.DATE.toString()) && !empty_line)
                {
                    ng.setDate(value);
                }
                else if (attribute.equals(Attributes.ORGANIZATION.toString()) && !empty_line)
                {
                    ng.setOrganization(value);
                }
                else if (attribute.equals(Attributes.LINES.toString()) && !empty_line)
                {
                    ng.setLines(value);
                }
                else if (attribute.equals(Attributes.REPLYTO.toString()) && !empty_line)
                {
                    ng.setReplyto(value);
                }
                else if (attribute.equals(Attributes.DISTRIBUTION.toString()) && !empty_line)
                {
                    ng.setDistribution(value);
                }
                else if (attribute.equals(Attributes.FOLLOWUPTO.toString()) && !empty_line)
                {
                    ng.setFollowupto(value);
                }
                else if (attribute.equals(Attributes.XREF.toString()) && !empty_line)
                {
                    String[] values = value.split(" ");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setXref(values[i]);
                    }
                    // delimiter _
                }
                else if (attribute.equals(Attributes.REFERENCES.toString()) && !empty_line)
                {
                    String[] values = value.split(" ");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setReferences(values[i]);
                    }
                    // delimiter _
                }
                else if (attribute.equals(Attributes.SENDER.toString()) && !empty_line)
                {
                    ng.setSender(value);
                }
                else if (attribute.equals(Attributes.NNTPPOSTINGHOST.toString()) && !empty_line)
                {
                    ng.setNntppostinghost(value);
                }
                else if (attribute.equals(Attributes.ARTICLEID.toString()) && !empty_line)
                {
                    ng.setArticleid(value);
                }
                else if (attribute.equals(Attributes.RETURNRECEIPTTO.toString()) && !empty_line)
                {
                    ng.setReturnreceiptto(value);
                }
                else if (attribute.equals(Attributes.NFID.toString()) && !empty_line)
                {
                    ng.setNfid(value);
                }
                else if (attribute.equals(Attributes.NFFROM.toString()) && !empty_line)
                {
                    ng.setNffrom(value);
                }
                else if (attribute.equals(Attributes.KEYWORDS.toString()) && !empty_line)
                {
                    String[] values = value.split(", ");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setNffrom(values[i]);
                    }
                    // delimiter ,_
                }
                else if(empty_line)
                {
                    // no meta data attribute associated with input
                    text_content.append(line);
                    text_content.append("\n");
                }
                else
                {
                    // ?
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
           e.printStackTrace();
        }

        ng.setText(text_content.toString());
        //System.out.println(ng.toString());
        return ng;
    }

    // Filter out the special characters
    // Convert tokens to lower case
    // Filter out stop words
    public void tokenizeText(Newsgroup ng)
    {
        String text = ng.getText();
        // replace all non alpha numerical characters
        // and convert result to lower case
        text = text.replaceAll("[^\\w\\s]", "");
        text = text.replaceAll("_", "");
        if(caseFold)
        {
            text = text.toLowerCase();
        }

        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<String> final_tokens = new ArrayList<String>();
        // add the tokens to the newsgroup arraylist tokens
        StringTokenizer tokenizer = new StringTokenizer(text);
        while(tokenizer.hasMoreTokens())
        {
            tokens.add(tokenizer.nextToken());
        }

        // filter stop words
        for (int i = 0; i < tokens.size(); i++)
        {
            // get the item as string
            boolean add = true;
            for (int j = 0; j < stopwords.getStopwords().length; j++)
            {
                //System.out.print(stopwords.getStopwords()[j] + " " + j + "\t");
                //System.out.println(tokens.get(i) + " " + i);
                if(removeStopwords)
                {
                    if (stopwords.getStopwords()[j].contains(tokens.get(i)))
                    {
                        //tokens.remove(i);
                        add = false;
                    }
                }
            }

            if(add)
            {
                //System.out.println("Adding " + tokens.get(i) + " to final");
                final_tokens.add(tokens.get(i));
            }
        }
        // finally add the filtered result to the newsgroup
        for(int i = 0; i < final_tokens.size(); i++)
        {
            if(stemming)
            {
                stemmer.setCurrent(final_tokens.get(i));
                if (stemmer.stem())
                {
                    String s = stemmer.getCurrent();
                    if (!isNumber(s))
                    {
                        ng.setTokens(s);
                        //System.out.println(s);
                    } else
                    {
                        // ?
                    }
                }
            }
            else
            {
                if (!isNumber(final_tokens.get(i)))
                {
                    ng.setTokens(final_tokens.get(i));
                }
            }
            //System.out.println(tokens.get(i));
        }
    }

    // Internal function
    // Check if sting is a number
    private static boolean isNumber(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
