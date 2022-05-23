package org.example.damages;

import com.jsoniter.annotation.JsonCreator;
import lombok.Value;

import java.util.List;

@Value
public class Types {
    List<Type> results;

    @JsonCreator
    public Types(List<Type> results) {
        this.results = results;
    }
}
