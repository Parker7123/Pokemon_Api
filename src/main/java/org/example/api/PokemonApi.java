package org.example.api;

import com.jsoniter.JsonIterator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.damages.Damage;
import org.example.damages.Type;
import org.example.damages.Types;
import org.example.input.Input;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.*;
import java.util.stream.Collectors;

public class PokemonApi {
    private final Map<String,Type> types = new HashMap<>();
    private final OkHttpClient client = new OkHttpClient.Builder().build();

    private final Set<String> typeNames = new HashSet<>();

    public PokemonApi() {
        loadTypes();
    }
    private void loadDamageType(String damageType) {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/type/" + damageType)
                .get().build();
        try(Response response = client.newCall(request).execute()) {
            String body = Objects.requireNonNull(response.body()).string();
            if(body.equals("Not Found")) return;
            Type type = JsonIterator.deserialize(body, Type.class);
            types.put(type.getName(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTypes() {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/type/")
                .get().build();
        try(Response response = client.newCall(request).execute()) {
            String body = Objects.requireNonNull(response.body()).string();
            JsonIterator.deserialize(body, Types.class).getResults()
                    .stream()
                    .map(Type::getName)
                    .forEach(typeNames::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<BigDecimal> getDamageMultiplier(Input input) {
        BigDecimal multiplier = BigDecimal.ONE;
        Type type = types.get(input.getType());

        for(String enemyType : input.getEnemies()) {
            if(!typeNames.contains(enemyType)) {
                return Optional.empty();
            }
            if(type.getDamageRelations()
                    .getNoDamageTo()
                    .stream()
                    .map(Damage::getName)
                    .collect(Collectors.toSet())
                    .contains(enemyType)) {
                multiplier = BigDecimal.ZERO;
                break;
            } else if(type.getDamageRelations()
                    .getDoubleDamageTo()
                    .stream()
                    .map(Damage::getName)
                    .collect(Collectors.toSet())
                    .contains(enemyType)) {
                multiplier = multiplier.multiply(BigDecimal.valueOf(2));
            } else if(type.getDamageRelations()
                    .getHalfDamageTo()
                    .stream()
                    .map(Damage::getName)
                    .collect(Collectors.toSet())
                    .contains(enemyType)) {
                multiplier = multiplier.multiply(new BigDecimal("0.5"));
            }
        }
        return Optional.of(multiplier);
    }

    public List<Optional<BigDecimal>> getDamageMultipliers(List<Input> inputList) {
        List<Optional<BigDecimal>> result = new ArrayList<>();
        inputList.stream()
                .map(Input::getType)
                .filter(typeNames::contains)
                .collect(Collectors.toSet())
                .forEach(this::loadDamageType);

        inputList.stream()
                .flatMap(input -> input.getEnemies().stream())
                .filter(typeNames::contains)
                .filter(type -> !types.containsKey(type))
                .collect(Collectors.toSet())
                .forEach(this::loadDamageType);

        for(Input input: inputList) {
            if(!typeNames.contains(input.getType())) {
                result.add(Optional.empty());
            } else {
                result.add(getDamageMultiplier(input));
            }
        }
        return result;
    }
}
