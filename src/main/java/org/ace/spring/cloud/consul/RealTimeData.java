package org.ace.spring.cloud.consul;

/**
 * Created by acouette on 27/05/16.
 */
public class RealTimeData {

    private final String content;

    public RealTimeData(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
