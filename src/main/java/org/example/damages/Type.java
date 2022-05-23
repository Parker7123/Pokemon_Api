package org.example.damages;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import lombok.Value;
import org.example.damages.DamageRelations;

@Value
public class Type {
    String name;
    DamageRelations damageRelations;

    @JsonCreator
    public Type(@JsonProperty("name")String name,
                @JsonProperty("damage_relations") DamageRelations damageRelations) {
        this.name = name;
        this.damageRelations = damageRelations;
    }
}