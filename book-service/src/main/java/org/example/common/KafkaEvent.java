package org.example.common;

import java.io.Serializable;

public class KafkaEvent implements Serializable {
    private String type;
    private String key;

    public KafkaEvent() {}

    public KafkaEvent(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }
}