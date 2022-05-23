package org.example.input;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public static List<Input> parse(File file) throws IOException, IllegalArgumentException {
        List<Input> inputList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while(reader.ready()) {
                String line = reader.readLine();
                inputList.add(parseLine(line));
            }
        }
        return inputList;
    }

    public static Input parseLine(String line) throws IllegalArgumentException {
        String[] split = line.split("->");
        if(split.length != 2) {
            throw new IllegalArgumentException("Invalid input line: " + line);
        }
        return new Input(split[0].strip(), Arrays.stream(split[1].strip().split(" "))
                .sequential()
                .map(String::strip)
                .collect(Collectors.toList()));
    }
}
