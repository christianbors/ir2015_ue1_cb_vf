package ir2015.ue1.model;

import com.google.gson.Gson;
import ir2015.ue1.parser.Attributes;

import java.util.ArrayList;

/**
 * Created by christianbors on 26/03/15.
 */
public class Newsgroup {
    private ArrayList<String> path; // can be multiple
    private String from;
    private ArrayList<String> newsgroups; // can be multiple
    private String subject;
    private String messageid;
    private String date;
    private String organization;
    private String lines;
    private String replyto;
    private String distribution;
    private String followupto;
    private ArrayList<String> xref; // can be multiple
    private ArrayList<String> references; // can be multiple
    private String sender;
    private String nntppostinghost;
    private String articleid;
    private String returnreceiptto;
    private String nfid;
    private String nffrom;
    private ArrayList<String> keywords; // can be multiple
    private String text;
    private ArrayList<String> tokens;
    //...

    public Newsgroup() {
        path = new ArrayList<String>();
        from = "";
        newsgroups =  new ArrayList<String>();
        subject = "";
        messageid = "";
        date = "";
        organization = "";
        lines = "";
        replyto = "";
        distribution = "";
        followupto = "";
        xref =  new ArrayList<String>();
        references =  new ArrayList<String>();
        sender = "";
        nntppostinghost = "";
        articleid = "";
        returnreceiptto = "";
        nfid = "";
        nffrom = "";
        keywords = new ArrayList<String>();
        text = "";
        tokens = new ArrayList<String>();
    }

    public ArrayList<String> getTokens() { return tokens; }

    public void setTokens(String token) { this.tokens.add(token); }

    public ArrayList<String> getKeywords() { return keywords; }

    public void setKeywords(String keyword) { this.keywords.add(keyword); }

    public String getNfid() {
        return nfid;
    }

    public void setNfid(String nfid) {
        this.nfid = nfid;
    }

    public String getNffrom() {
        return nffrom;
    }

    public void setNffrom(String nffrom) {
        this.nffrom = nffrom;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path.add(path);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ArrayList<String> getNewsgroups() {
        return newsgroups;
    }

    public void setNewsgroups(String newsgroup) { this.newsgroups.add(newsgroup); }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getReplyto() {
        return replyto;
    }

    public void setReplyto(String replyto) {
        this.replyto = replyto;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getFollowupto() {
        return followupto;
    }

    public void setFollowupto(String followupto) {
        this.followupto = followupto;
    }

    public ArrayList<String> getXref() {
        return xref;
    }

    public void setXref(String xref) { this.xref.add(xref); }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(String reference) { this.references.add(reference); }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public String getNntppostinghost() { return nntppostinghost; }

    public void setNntppostinghost(String nntppostinghost) {
        this.nntppostinghost = nntppostinghost;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getReturnreceiptto() {
        return returnreceiptto;
    }

    public void setReturnreceiptto(String returnreceiptto) {
        this.returnreceiptto = returnreceiptto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    // Override of toString method
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder();
        if(!xref.isEmpty())
        {
            out.append(Attributes.XREF.toString() + ": ");
            for(int i = 0; i < xref.size(); i++)
            {
               out.append(xref.get(i) + " ");
            }
            out.append("\n");
        }
        if(!path.isEmpty())
        {
            out.append(Attributes.PATH.toString() + ": ");

            for(int i = 0; i < path.size(); i++)
            {
                out.append(path.get(i) + "!");
            }
            out.append("\n");
        }
        if(!from.isEmpty())
        {
            out.append(Attributes.FROM.toString());
            out.append(": " + from + "\n");
        }
        if(!newsgroups.isEmpty())
        {
            out.append(Attributes.NEWSGROUPS.toString()+ ": ");

            for(int i = 0; i < newsgroups.size(); i++)
            {
                out.append(newsgroups.get(i) + ",");
            }
            out.append("\n");
        }
        if(!subject.isEmpty())
        {
            out.append(Attributes.SUBJECT.toString());
            out.append(": " + subject + "\n");
        }
        if(!keywords.isEmpty())
        {
            out.append(Attributes.KEYWORDS.toString() + ": ");

            for(int i = 0; i < keywords.size(); i++)
            {
                out.append(keywords.get(i) + ", ");
            }
            out.append("\n");

        }
        if(!messageid.isEmpty())
        {
            out.append(Attributes.MESSAGEID.toString());
            out.append(": " + messageid + "\n");
        }
        if(!date.isEmpty())
        {
            out.append(Attributes.DATE.toString());
            out.append(": " + date + "\n");
        }
        if(!organization.isEmpty())
        {
            out.append(Attributes.ORGANIZATION.toString());
            out.append(": " + organization + "\n");
        }
        if(!articleid.isEmpty())
        {
            out.append(Attributes.ARTICLEID.toString());
            out.append(": " + articleid + "\n");
        }
        if(!references.isEmpty())
        {
            out.append(Attributes.REFERENCES.toString() + ": ");

            for(int i = 0; i < references.size(); i++)
            {
                out.append(references.get(i) + " ");
            }
            out.append("\n");
        }
        if(!sender.isEmpty())
        {
            out.append(Attributes.SENDER.toString());
            out.append(": " + sender + "\n");
        }
        if(!followupto.isEmpty())
        {
            out.append(Attributes.FOLLOWUPTO.toString());
            out.append(": " + followupto + "\n");
        }
        if(!replyto.isEmpty())
        {
            out.append(Attributes.REPLYTO.toString());
            out.append(": " + replyto + "\n");
        }
        if(!distribution.isEmpty())
        {
            out.append(Attributes.DISTRIBUTION.toString());
            out.append(": " + distribution + "\n");
        }
        if(!returnreceiptto.isEmpty())
        {
            out.append(Attributes.RETURNRECEIPTTO.toString());
            out.append(": " + returnreceiptto + "\n");
        }
        if(!lines.isEmpty())
        {
            out.append(Attributes.LINES.toString());
            out.append(": " + lines + "\n");
        }
        if(!nntppostinghost.isEmpty())
        {
            out.append(Attributes.NNTPPOSTINGHOST.toString());
            out.append(": " + nntppostinghost+ "\n");
        }
        if(!nffrom.isEmpty())
        {
            out.append(Attributes.NFFROM.toString());
            out.append(": " + nffrom + "\n");
        }
        if(!nfid.isEmpty())
        {
            out.append(Attributes.NFID.toString());
            out.append(": " + nfid + "\n");
        }
        out.append(text + "\n");
        return out.toString();

    }
}
