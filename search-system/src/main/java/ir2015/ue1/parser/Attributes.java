package ir2015.ue1.parser;

/**
 * Created by Velitchko on 3/28/2015.
 */
public enum Attributes {
    PATH, FROM, NEWSGROUPS, SUBJECT, MESSAGEID, DATE, ORGANIZATION, LINES, REPLYTO, DISTRIBUTION, FOLLOWUPTO, XREF, REFERENCES, SENDER, NNTPPOSTINGHOST, ARTICLEID, RETURNRECEIPTTO, NFID, NFFROM, KEYWORDS;

// Enum meta data
    public String toString()
    {
        switch(this)
        {
            case PATH: return "Path";
            case FROM: return "From";
            case NEWSGROUPS: return "Newsgroups";
            case SUBJECT: return "Subject";
            case KEYWORDS: return "Keywords";
            case MESSAGEID: return "Message-ID";
            case DATE: return "Date";
            case ORGANIZATION: return "Organization";
            case LINES: return "Lines";
            case REPLYTO: return "Reply-To";
            case DISTRIBUTION: return "Distribution";
            case FOLLOWUPTO: return "Followup-To";
            case XREF: return "Xref";
            case REFERENCES: return "References";
            case SENDER: return "Sender";
            case NNTPPOSTINGHOST: return "Nntp-Posting-Host";
            case ARTICLEID: return "Article-ID";
            case RETURNRECEIPTTO: return "Return-Receipt-To";
            case NFFROM: return "Nf-From";
            case NFID: return "Nf-ID";
            default: return "Unknown";
        }
    }
}
