package com.fulldive.reader.data.remote;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Parent class for the root-node </RSS>.
 */
@Root(name = "rss", strict = false)
public class RSS{

    @Element(name = "channel")
    private Channel channel;

    public RSS() {
    }

    public RSS(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(final Channel channel) {
        this.channel = channel;
    }

}
