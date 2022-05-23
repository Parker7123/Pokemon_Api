package org.example.damages;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonObject;
import lombok.*;

@Value
public class Damage {
    String name;
    String url;

    @JsonCreator
    public Damage(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
