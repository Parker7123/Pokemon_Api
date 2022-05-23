package org.example.input;

import lombok.Value;

import java.util.List;

@Value
public class Input {
    String type;
    List<String> enemies;
}
