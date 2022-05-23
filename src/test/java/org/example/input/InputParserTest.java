package org.example.input;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void parseRightLine() {
        Input input = InputParser.parseLine("psychic -> poison dark");
        assertEquals(new Input("psychic", List.of("poison","dark")), input);
    }
    @Test
    void parseWrongLine() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseLine("psychic, poison dark"));
    }
    @Test
    void parseWrongLine2() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseLine("psychic ->"));
    }
}