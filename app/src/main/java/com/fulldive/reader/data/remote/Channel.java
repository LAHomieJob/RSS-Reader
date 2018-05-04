package com.fulldive.reader.data.remote;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


/**
 * Main class for the node </channel> from RSS response.
 */

@Root(name = "channel", strict = false)
public class Channel{

    @ElementList(inline = true, name = "item")
    private List<Item> items;

    public Channel() {
    }

    public Channel(final List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }
}
