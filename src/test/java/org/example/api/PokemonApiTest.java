package org.example.api;

import org.example.input.Input;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonApiTest {

    @Test
    void getDamageMultipliers() {
        PokemonApi pokemonApi = new PokemonApi();
        Input input = new Input("psychic", List.of("poison", "dark"));
        var output = pokemonApi.getDamageMultipliers(List.of(input));
        assertTrue(output.get(0).isPresent());
        assertEquals(BigDecimal.ZERO, output.get(0).get());
    }

    @Test
    void getDamageMultipliersWrong() {
        PokemonApi pokemonApi = new PokemonApi();
        Input input = new Input("qwerty", List.of("poison", "dark"));
        var output = pokemonApi.getDamageMultipliers(List.of(input));
        assertTrue(output.get(0).isEmpty());
    }

    @Test
    void getDamageMultipliersWrong2() {
        PokemonApi pokemonApi = new PokemonApi();
        Input input = new Input("physic", List.of("qweqwe", ""));
        var output = pokemonApi.getDamageMultipliers(List.of(input));
        assertTrue(output.get(0).isEmpty());
    }

    @Test
    void getDamageMultipliersWrong3() {
        PokemonApi pokemonApi = new PokemonApi();
        Input input = new Input("physic", List.of("qweqwe", ""));
        var output = pokemonApi.getDamageMultipliers(List.of(input));
        assertTrue(output.get(0).isEmpty());
    }
}