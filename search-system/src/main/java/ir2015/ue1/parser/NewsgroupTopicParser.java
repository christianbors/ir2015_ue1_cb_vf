package ir2015.ue1.parser;

import ir2015.ue1.model.Newsgroup;

import java.io.*;


/**
 * Created by christianbors on 22/03/15.
 */
public class NewsgroupTopicParser {

    // constructor
    public NewsgroupTopicParser()
    {

    }

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
                    try {
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
                if (attribute.equals(Attributes.PATH.toString()) && !empty_line) {
                    String[] values = value.split("!");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setPath(values[i]);
                    }
                    //delimiter !
                }
                else if (attribute.equals(Attributes.FROM.toString()) && !empty_line) {
                    ng.setFrom(value);
                }
                else if (attribute.equals(Attributes.NEWSGROUPS.toString()) && !empty_line) {
                    String[] values = value.split(",");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setNewsgroups(values[i]);
                    }
                    // delimiter ,
                }
                else if (attribute.equals(Attributes.SUBJECT.toString()) && !empty_line) {
                    ng.setSubject(value);
                }
                else if (attribute.equals(Attributes.MESSAGEID.toString()) && !empty_line) {
                    ng.setMessageid(value);
                }
                else if (attribute.equals(Attributes.DATE.toString()) && !empty_line) {
                    ng.setDate(value);
                }
                else if (attribute.equals(Attributes.ORGANIZATION.toString()) && !empty_line) {
                    ng.setOrganization(value);
                }
                else if (attribute.equals(Attributes.LINES.toString()) && !empty_line) {
                    ng.setLines(value);
                }
                else if (attribute.equals(Attributes.REPLYTO.toString()) && !empty_line) {
                    ng.setReplyto(value);
                }
                else if (attribute.equals(Attributes.DISTRIBUTION.toString()) && !empty_line) {
                    ng.setDistribution(value);
                }
                else if (attribute.equals(Attributes.FOLLOWUPTO.toString()) && !empty_line) {
                    ng.setFollowupto(value);
                }
                else if (attribute.equals(Attributes.XREF.toString()) && !empty_line) {
                    String[] values = value.split(" ");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setXref(values[i]);
                    }
                    // delimiter _
                }
                else if (attribute.equals(Attributes.REFERENCES.toString()) && !empty_line) {
                    String[] values = value.split(" ");
                    for(int i = 0; i < values.length; i++)
                    {
                        ng.setReferences(values[i]);
                    }
                    // delimiter _
                }
                else if (attribute.equals(Attributes.SENDER.toString()) && !empty_line) {
                    ng.setSender(value);
                }
                else if (attribute.equals(Attributes.NNTPPOSTINGHOST.toString()) && !empty_line) {
                    ng.setNntppostinghost(value);
                }
                else if (attribute.equals(Attributes.ARTICLEID.toString()) && !empty_line) {
                    ng.setArticleid(value);
                }
                else if (attribute.equals(Attributes.RETURNRECEIPTTO.toString()) && !empty_line) {
                    ng.setReturnreceiptto(value);
                }
                else if (attribute.equals(Attributes.NFID.toString()) && !empty_line) {
                    ng.setNfid(value);
                }
                else if (attribute.equals(Attributes.NFFROM.toString()) && !empty_line) {
                    ng.setNffrom(value);
                }
                else if (attribute.equals(Attributes.KEYWORDS.toString()) && !empty_line) {
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
        catch (IOException e) {
           e.printStackTrace();
        }

        ng.setText(text_content.toString());
        return ng;
    }
}
