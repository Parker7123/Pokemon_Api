package org.example;

import com.jsoniter.JsonIterator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.damages.Type;
import org.example.damages.Types;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.*;

public class PokemonApi {
    private final Map<String,Type> types = new HashMap<>();
    private final OkHttpClient client = new OkHttpClient.Builder().build();

    private Set<String> typeNames = new HashSet<>();

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
                    .forEach(name -> typeNames.add(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
