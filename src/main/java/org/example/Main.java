package org.example;

import org.example.api.PokemonApi;
import org.example.input.InputParser;
import org.example.output.OutputParser;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: <input-filee> <output-file>");
            System.exit(1);
        }
        PokemonApi pokemonApi = new PokemonApi();
        File file = new File(args[0]);
        File outFile = new File(args[1]);
        try {
            OutputParser.write(pokemonApi.getDamageMultipliers(InputParser.parse(file)), outFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}