package com.fulldive.reader.data.remote;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Class for news </item> in RSS responses.
 */
@Root(name = "item", strict = false)
public class Item{

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "description")
    private String description;

    public Item() {
    }

    public Item(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(final String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result =
                "\n" + "Title " + getTitle() + "\n "
                        + "Link " + getLink() + "\n "
                        + "description " + getDescription() + "\n "
                        + "pubDate " + getPubDate() + "\n ";
        return result;
    }
}
