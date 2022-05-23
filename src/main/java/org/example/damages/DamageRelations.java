package org.example.damages;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import org.example.damages.Damage;

import java.util.List;

@Value
public class DamageRelations {
    List<Damage> noDamageTo;
    List<Damage> halfDamageTo;
    List<Damage> doubleDamageTo;

    @JsonCreator
    public DamageRelations(@JsonProperty("no_damage_to") List<Damage> noDamageTo,
                           @JsonProperty("half_damage_to") List<Damage> halfDamageTo,
                           @JsonProperty("double_damage_to") List<Damage> doubleDamageTo) {
        this.noDamageTo = noDamageTo;
        this.halfDamageTo = halfDamageTo;
        this.doubleDamageTo = doubleDamageTo;
    }
}